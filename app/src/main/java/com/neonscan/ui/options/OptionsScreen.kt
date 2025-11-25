package com.neonscan.ui.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.data.settings.SettingsRepository
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.components.NeonSecondaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.ui.theme.NeonGray
import com.neonscan.util.FileManager
import com.neonscan.util.ScanCache
import com.neonscan.util.shareFile
import android.provider.MediaStore
import android.content.ContentValues
import com.neonscan.util.AdsManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(
    navController: NavController,
    repo: DocumentRepository,
    settingsRepository: SettingsRepository,
    vm: OptionsViewModel = viewModel(factory = OptionsViewModelFactory(repo, FileManager(LocalContext.current)))
) {
    val bitmap = ScanCache.lastBitmap ?: return
    var saveAndShare by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var format by remember { mutableStateOf(DocumentFormat.PDF) }
    val existingDoc = ScanCache.currentDocument

    // load default format from settings
    val settingsFlow = settingsRepository.settings
    LaunchedEffect(Unit) {
        settingsFlow.collect { settings ->
            format = settings.defaultFormat
            vm.title = vm.title // trigger recomposition
        }
    }
    LaunchedEffect(existingDoc) {
        existingDoc?.let {
            vm.title = it.title
            format = it.primaryFormat
            vm.exportPdf = it.primaryFormat == DocumentFormat.PDF
            vm.exportJpg = it.primaryFormat == DocumentFormat.JPG
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeonBlack)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
        Text("Options du document", color = Color.White)
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.Close, contentDescription = "Fermer", tint = NeonApple)
            }
        }
        Spacer(Modifier.height(12.dp))
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(16.dp))
        Text("Format", color = Color.White)
        Spacer(Modifier.height(8.dp))
        FormatToggle(
            selected = format,
            onSelect = { selected ->
                format = selected
                vm.exportPdf = selected == DocumentFormat.PDF
                vm.exportJpg = selected == DocumentFormat.JPG
            }
        )
        Spacer(Modifier.height(12.dp))
        androidx.compose.material3.OutlinedTextField(
            value = vm.title,
            onValueChange = { vm.title = it },
            label = { Text("Titre", color = NeonGray) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = NeonApple,
                unfocusedBorderColor = NeonGray,
                cursorColor = NeonApple
            )
        )
        Spacer(Modifier.height(24.dp))
        NeonPrimaryButton(
            text = "Enregistrer dans l'app",
            onClick = {
                vm.exportPdf = format == DocumentFormat.PDF
                vm.exportJpg = format == DocumentFormat.JPG
                if (existingDoc != null) {
                    vm.updateExisting(existingDoc, bitmap, format) {
                        ScanCache.currentDocument = null
                        AdsManager.maybeShowInterstitial(context as? android.app.Activity ?: return@updateExisting)
                        navController.navigate("home") { popUpTo("home") { inclusive = true } }
                    }
                } else {
                    vm.save(bitmap) {
                        ScanCache.currentDocument = null
                        AdsManager.maybeShowInterstitial(context as? android.app.Activity ?: return@save)
                        navController.navigate("home") { popUpTo("home") { inclusive = true } }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        NeonSecondaryButton(
            text = "Partager directement",
            onClick = {
                saveAndShare = true
                vm.exportPdf = format == DocumentFormat.PDF
                vm.exportJpg = format == DocumentFormat.JPG
                vm.save(bitmap) {
                    shareFile(context, java.io.File(it.filePathPrimary))
                    AdsManager.maybeShowInterstitial(context as? android.app.Activity ?: return@save)
                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun FormatToggle(selected: DocumentFormat, onSelect: (DocumentFormat) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
        FormatChip("PDF", selected == DocumentFormat.PDF) { onSelect(DocumentFormat.PDF) }
        FormatChip("JPG", selected == DocumentFormat.JPG) { onSelect(DocumentFormat.JPG) }
    }
}

@Composable
private fun FormatChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val borderColor = if (selected) NeonApple else NeonGray
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.4.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selected) NeonApple.copy(alpha = 0.15f) else Color.Transparent,
            contentColor = if (selected) NeonApple else NeonGray
        )
    ) {
        Text(label, color = Color.White)
    }
}

// Phone save removed
