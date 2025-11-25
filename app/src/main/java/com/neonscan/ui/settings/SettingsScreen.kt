package com.neonscan.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.settings.ScanQuality
import com.neonscan.data.settings.SettingsRepository
import com.neonscan.ui.components.NeonSecondaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.ui.theme.NeonGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, settingsRepository: SettingsRepository) {
    val vm = viewModel<SettingsViewModel>(factory = SettingsViewModelFactory(settingsRepository))
    val settings by vm.settings.collectAsState()
    com.neonscan.util.AdsManager.frequency = settings.interstitialFrequency

    Scaffold(
        containerColor = NeonBlack,
        topBar = {
            TopAppBar(
                title = { Text("Paramètres", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = NeonApple)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NeonBlack)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Qualité du scan", color = Color.White)
            QualityRow(
                selected = settings.quality,
                onSelect = vm::setQuality
            )
            Spacer(Modifier.height(8.dp))
            Text("Format par défaut", color = Color.White)
            FormatRow(
                selected = settings.defaultFormat,
                onSelect = vm::setFormat
            )
            Spacer(Modifier.height(8.dp))
            Text("Fréquence des pubs interstitielles", color = Color.White)
            FrequencyRow(
                selected = settings.interstitialFrequency,
                onSelect = vm::setFrequency
            )
            Spacer(Modifier.height(12.dp))
            NeonSecondaryButton(text = "Fermer", onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun QualityRow(selected: ScanQuality, onSelect: (ScanQuality) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(ScanQuality.LOW, ScanQuality.STANDARD, ScanQuality.HIGH).forEach { q ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(q) }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(qLabel(q), color = NeonGray)
                RadioButton(selected = selected == q, onClick = { onSelect(q) }, colors = RadioButtonDefaults.colors(selectedColor = NeonApple))
            }
        }
    }
}

@Composable
private fun FormatRow(selected: DocumentFormat, onSelect: (DocumentFormat) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        DocumentFormat.values().filter { it != DocumentFormat.OCR }.forEach { f ->
            Row(
                modifier = Modifier.clickable { onSelect(f) },
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButton(selected = selected == f, onClick = { onSelect(f) }, colors = RadioButtonDefaults.colors(selectedColor = NeonApple))
                Text(f.name, color = if (selected == f) NeonApple else NeonGray)
            }
        }
    }
}

@Composable
private fun FrequencyRow(selected: Int, onSelect: (Int) -> Unit) {
    val options = listOf(1, 3, 5)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        options.forEach { freq ->
            Row(modifier = Modifier.clickable { onSelect(freq) }) {
                RadioButton(selected = selected == freq, onClick = { onSelect(freq) }, colors = RadioButtonDefaults.colors(selectedColor = NeonApple))
                Text("$freq scans", color = if (selected == freq) NeonApple else NeonGray, modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

private fun qLabel(q: ScanQuality): String = when (q) {
    ScanQuality.LOW -> "Basse"
    ScanQuality.STANDARD -> "Standard"
    ScanQuality.HIGH -> "Haute"
}
