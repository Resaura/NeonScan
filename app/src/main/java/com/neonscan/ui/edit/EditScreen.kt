package com.neonscan.ui.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neonscan.data.model.ScanFilter
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.components.NeonSecondaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.util.ImageFilters
import com.neonscan.util.ScanCache
import com.neonscan.util.rotate90

@Composable
fun EditScreen(
    navController: NavController,
    vm: EditViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val original = ScanCache.lastBitmap ?: return
    var preview by remember { mutableStateOf(original) }
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeonBlack)
            .padding(16.dp)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            bitmap = preview.asImageBitmap(),
            contentDescription = "Aperçu",
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            ScanFilter.values().forEach { filter ->
                NeonSecondaryButton(
                    text = filterLabel(filter),
                    onClick = {
                        vm.filter = filter
                        preview = ImageFilters.apply(filter, original)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = true
                )
            }
            IconButtonLarge {
                vm.rotate()
                preview = preview.rotate90()
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NeonSecondaryButton(text = "Annuler", onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(12.dp))
            NeonPrimaryButton(text = "Suivant", onClick = {
                ScanCache.lastBitmap = preview
                navController.navigate("options")
            }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun IconButtonLarge(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(56.dp)) {
        Icon(Icons.Default.RotateRight, contentDescription = "Rotation", tint = NeonApple, modifier = Modifier.size(32.dp))
    }
}

private fun filterLabel(filter: ScanFilter): String = when (filter) {
    ScanFilter.Original -> "Original"
    ScanFilter.BnW -> "Noir & Blanc"
    ScanFilter.Enhanced -> "Document"
}
