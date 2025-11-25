package com.neonscan.util

import android.graphics.Bitmap

object ScanCache {
    var lastBitmap: Bitmap? = null
    var currentDocument: com.neonscan.data.local.DocumentEntity? = null
}
