package com.neonscan.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.ui.components.NeonCard
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.ui.theme.NeonGray
import com.neonscan.util.AdMobBanner
import com.neonscan.util.shareFile
import com.neonscan.util.simpleDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, repo: DocumentRepository) {
    val vm = viewModel<HomeViewModel>(factory = HomeViewModelFactory(repo))
    val docs by vm.docs.collectAsState()
    val context = LocalContext.current

    Scaffold(
        containerColor = NeonBlack,
        topBar = {
            TopAppBar(
                title = { Text("NeonScan", color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Paramètres", tint = NeonApple)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("scan") },
                containerColor = NeonApple,
                contentColor = NeonBlack
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Scan")
            }
        },
        bottomBar = { AdMobBanner(adUnitId = com.neonscan.util.AdsManager.TEST_BANNER) }
    ) { padding ->
        if (docs.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { Text("Aucun scan pour l'instant", color = Color.White) }
        } else {
            LazyColumn(modifier = Modifier.padding(padding).padding(bottom = 56.dp)) {
                items(items = docs, key = { doc -> doc.id }) { doc: DocumentEntity ->
                    DocumentRow(
                        doc = doc,
                        onClick = { navController.navigate("detail/${doc.id}") },
                        onDelete = { vm.delete(doc) },
                        onShare = {
                            val file = java.io.File(doc.filePathPrimary)
                            shareFile(context, file)
                        },
                        onRename = { newTitle -> vm.rename(doc, newTitle) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DocumentRow(
    doc: DocumentEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    onRename: (String) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var renameDialog by remember { mutableStateOf(false) }
    var deleteDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf(doc.title) }

    NeonCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(onClick = onClick, onLongClick = onDelete),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (doc.primaryFormat == DocumentFormat.PDF) Icons.Default.PictureAsPdf else Icons.Default.Image,
                contentDescription = null,
                tint = NeonApple
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doc.title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White)
                Text(
                    doc.createdAt.simpleDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = NeonGray
                )
            }
            IconButton(onClick = onShare) { Icon(Icons.Outlined.Share, contentDescription = "Partager", tint = NeonApple) }
            IconButton(onClick = { menuExpanded = true }) { Icon(Icons.Default.MoreVert, contentDescription = "Plus", tint = NeonApple) }
            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(text = { Text("Renommer") }, leadingIcon = { Icon(Icons.Outlined.Edit, null) }, onClick = {
                    menuExpanded = false
                    renameDialog = true
                })
                DropdownMenuItem(text = { Text("Supprimer") }, leadingIcon = { Icon(Icons.Default.Delete, null) }, onClick = {
                    menuExpanded = false
                    deleteDialog = true
                })
                DropdownMenuItem(text = { Text("Ouvrir") }, leadingIcon = { Icon(Icons.Default.CameraAlt, null) }, onClick = {
                    menuExpanded = false
                    onClick()
                })
            }
        }
    }

    if (renameDialog) {
        AlertDialog(
            onDismissRequest = { renameDialog = false },
            confirmButton = {
                NeonPrimaryButton(text = "Valider", onClick = {
                    renameDialog = false
                    onRename(newTitle)
                })
            },
            dismissButton = {
                IconButton(onClick = { renameDialog = false }) {
                    Icon(Icons.Default.Close, contentDescription = "Fermer", tint = NeonApple)
                }
            },
            text = {
                Column {
                    Text("Renommer", color = Color.White)
                    Spacer(Modifier.height(8.dp))
                    TextField(value = newTitle, onValueChange = { newTitle = it }, singleLine = true)
                }
            },
            containerColor = NeonBlack
        )
    }
    if (deleteDialog) {
        AlertDialog(
            onDismissRequest = { deleteDialog = false },
            confirmButton = {
                NeonPrimaryButton(text = "Supprimer", onClick = {
                    deleteDialog = false
                    onDelete()
                })
            },
            dismissButton = {
                IconButton(onClick = { deleteDialog = false }) {
                    Icon(Icons.Default.Close, contentDescription = "Fermer", tint = NeonApple)
                }
            },
            text = { Text("Supprimer ce scan ?", color = Color.White) },
            containerColor = NeonBlack
        )
    }
}
