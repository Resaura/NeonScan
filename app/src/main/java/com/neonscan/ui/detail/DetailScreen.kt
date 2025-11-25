package com.neonscan.ui.detail

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
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
                    val bitmap = android.graphics.BitmapFactory.decodeFile(it.filePathPrimary)
                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(320.dp)
                        )
                    }
                } else {
                    Text("PDF disponible : ${it.filePathPrimary}", style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { shareFile(context, File(it.filePathPrimary)) }) { Text("Partager") }
                    OutlinedButton(onClick = { vm.convert() }) { Text("Convertir") }
                    OutlinedButton(onClick = { vm.delete(); navController.popBackStack() }) { Text("Supprimer") }
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
    }
}
