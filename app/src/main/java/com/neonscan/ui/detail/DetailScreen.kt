package com.neonscan.ui.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.util.ScanCache
import com.neonscan.util.shareFile
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    repo: DocumentRepository,
    id: String,
    vm: DetailViewModel = viewModel(factory = DetailViewModelFactory(repo, id))
) {
    val state = vm.state.collectAsState()
    val doc = state.value
    val context = LocalContext.current
    val previewBitmap = remember(doc) { doc?.let { loadPreview(it) } }
    var confirmDelete by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(doc?.title ?: "Document") },
                navigationIcon = {
                    OutlinedButton(onClick = { navController.popBackStack() }) { Text("Retour") }
                }
            )
        }
    ) { padding ->
        doc?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                if (it.primaryFormat == DocumentFormat.JPG) {
                    previewBitmap?.let { bmp ->
                        Image(
                            bitmap = bmp.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(320.dp)
                        )
                    }
                } else {
                    previewBitmap?.let { bmp ->
                        Image(
                            bitmap = bmp.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(320.dp)
                        )
                    } ?: Text("PDF disponible : ${it.filePathPrimary}", style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { shareFile(context, File(it.filePathPrimary)) }) { Text("Partager") }
                    OutlinedButton(onClick = {
                        previewBitmap?.let { bmp ->
                            ScanCache.lastBitmap = bmp
                            ScanCache.currentDocument = it
                            navController.navigate("edit")
                        }
                    }) { Text("Modifier") }
                    OutlinedButton(onClick = { confirmDelete = true }) { Text("Supprimer") }
                }
            }
        } ?: run {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Document introuvable", modifier = Modifier.padding(16.dp))
            }
        }

        if (confirmDelete) {
            AlertDialog(
                onDismissRequest = { confirmDelete = false },
                confirmButton = {
                    OutlinedButton(onClick = {
                        confirmDelete = false
                        vm.delete()
                        navController.popBackStack()
                    }) { Text("Supprimer") }
                },
                dismissButton = {
                    OutlinedButton(onClick = { confirmDelete = false }) { Text("Annuler") }
                },
                text = { Text("Confirmer la suppression ?", color = androidx.compose.ui.graphics.Color.White) },
                containerColor = com.neonscan.ui.theme.NeonBlack
            )
        }
    }
}

private fun loadPreview(doc: com.neonscan.data.local.DocumentEntity): Bitmap? {
    return when (doc.primaryFormat) {
        DocumentFormat.JPG -> BitmapFactory.decodeFile(doc.filePathPrimary)
        DocumentFormat.PDF -> renderPdfFirstPage(doc.filePathPrimary)
        else -> null
    }
}

private fun renderPdfFirstPage(path: String): Bitmap? {
    return try {
        val file = File(path)
        val pfd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = android.graphics.pdf.PdfRenderer(pfd)
        if (renderer.pageCount > 0) {
            val page = renderer.openPage(0)
            val width = page.width
            val height = page.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, android.graphics.pdf.PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            renderer.close()
            pfd.close()
            bitmap
        } else {
            renderer.close()
            pfd.close()
            null
        }
    } catch (_: Exception) {
        null
    }
}
