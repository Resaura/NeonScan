package com.neonscan.ui.edit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.graphics.Bitmap
import com.neonscan.data.model.ScanFilter
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.components.NeonSecondaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.util.ImageFilters
import com.neonscan.util.ScanCache
import com.neonscan.util.rotate90

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditScreen(
    navController: NavController,
    vm: EditViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val original = ScanCache.lastBitmap ?: return
    var currentBitmap by remember { mutableStateOf(original) }
    var preview by remember { mutableStateOf(original) }
    val scroll = rememberScrollState()

    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var handles by remember {
        mutableStateOf(
            ScanCache.lastCorners ?: listOf(
                0.2f to 0.25f,
                0.8f to 0.25f,
                0.8f to 0.75f,
                0.2f to 0.75f
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeonBlack)
            .padding(16.dp)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .clip(MaterialTheme.shapes.medium)
                .onSizeChanged { containerSize = it }
        ) {
            Image(
                bitmap = preview.asImageBitmap(),
                contentDescription = "Aperçu",
                modifier = Modifier.fillMaxSize()
            )
            CropOverlay(
                handles = handles,
                containerSize = containerSize,
                onHandleMove = { index, pos ->
                    val updated = handles.toMutableList()
                    updated[index] = pos
                    handles = updated
                }
            )
        }
        Spacer(Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ScanFilter.values().forEach { filter ->
                NeonSecondaryButton(
                    text = filterLabel(filter),
                    onClick = {
                        vm.filter = filter
                        preview = ImageFilters.apply(filter, currentBitmap)
                    },
                    enabled = true,
                    maxLines = 2,
                    selected = vm.filter == filter
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                vm.rotate()
                currentBitmap = currentBitmap.rotate90()
                preview = ImageFilters.apply(vm.filter, currentBitmap)
            }) {
                Icon(Icons.Default.RotateRight, contentDescription = "Rotation", tint = NeonApple, modifier = Modifier.size(32.dp))
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
                val cropped = cropBitmapWithHandles(preview, handles)
                ScanCache.lastBitmap = cropped
                ScanCache.lastCorners = handles
                navController.navigate("options")
            }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun CropOverlay(
    handles: List<Pair<Float, Float>>,
    containerSize: IntSize,
    onHandleMove: (Int, Pair<Float, Float>) -> Unit
) {
    if (containerSize == IntSize.Zero) return
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val points = handles.map { (nx, ny) -> Offset(nx * containerSize.width, ny * containerSize.height) }
        val path = Path().apply {
            moveTo(points[0].x, points[0].y)
            points.drop(1).forEach { lineTo(it.x, it.y) }
            close()
        }
        drawPath(path, color = NeonApple, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()))
        points.forEachIndexed { index, p ->
            drawCircle(color = Color.White, radius = 10.dp.toPx(), center = p, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()))
        }
    }
    handles.forEachIndexed { index, handle ->
        DraggableHandle(
            normalized = handle,
            containerSize = containerSize,
            onMove = { onHandleMove(index, it) }
        )
    }
}

@Composable
private fun DraggableHandle(
    normalized: Pair<Float, Float>,
    containerSize: IntSize,
    onMove: (Pair<Float, Float>) -> Unit
) {
    val posX = normalized.first * containerSize.width
    val posY = normalized.second * containerSize.height
    Box(
        modifier = Modifier
            .offset(x = posX.dp, y = posY.dp)
            .size(28.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, drag ->
                    change.consume()
                    val nx = ((posX + drag.x) / containerSize.width).coerceIn(0f, 1f)
                    val ny = ((posY + drag.y) / containerSize.height).coerceIn(0f, 1f)
                    onMove(nx to ny)
                }
            }
    )
}

private fun cropBitmapWithHandles(bitmap: android.graphics.Bitmap, handles: List<Pair<Float, Float>>): android.graphics.Bitmap {
    if (handles.size < 4) return bitmap
    val minX = handles.minOf { it.first }.coerceIn(0f, 1f)
    val maxX = handles.maxOf { it.first }.coerceIn(0f, 1f)
    val minY = handles.minOf { it.second }.coerceIn(0f, 1f)
    val maxY = handles.maxOf { it.second }.coerceIn(0f, 1f)
    val left = (minX * bitmap.width).toInt().coerceAtLeast(0)
    val top = (minY * bitmap.height).toInt().coerceAtLeast(0)
    val right = (maxX * bitmap.width).toInt().coerceAtMost(bitmap.width)
    val bottom = (maxY * bitmap.height).toInt().coerceAtMost(bitmap.height)
    if (right - left <= 0 || bottom - top <= 0) return bitmap
    return Bitmap.createBitmap(bitmap, left, top, right - left, bottom - top)
}

private fun filterLabel(filter: ScanFilter): String = when (filter) {
    ScanFilter.Original -> "Original"
    ScanFilter.BnW -> "Noir & Blanc"
    ScanFilter.Enhanced -> "Document"
}
