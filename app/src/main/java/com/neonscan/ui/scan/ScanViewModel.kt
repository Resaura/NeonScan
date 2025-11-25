package com.neonscan.ui.scan

import android.graphics.Bitmap
import android.graphics.PointF
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ScanViewModel : ViewModel() {
    var detectedPolygon by mutableStateOf<List<PointF>>(emptyList())
        private set
    var lastBitmap by mutableStateOf<Bitmap?>(null)
        private set

    fun onDocumentDetected(corners: List<PointF>) {
        detectedPolygon = corners
    }

    fun onShot(bitmap: Bitmap) {
        lastBitmap = bitmap
    }
}
