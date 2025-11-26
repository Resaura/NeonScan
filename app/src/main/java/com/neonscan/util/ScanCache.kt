package com.neonscan.util

import android.graphics.Bitmap

object ScanCache {
    var lastBitmap: Bitmap? = null
    var currentDocument: com.neonscan.data.local.DocumentEntity? = null
    var lastCorners: List<Pair<Float, Float>>? = null // normalized points (0..1)
}
