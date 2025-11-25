package com.neonscan.data.model

import android.graphics.Bitmap
import android.graphics.PointF

data class ScanResult(
    val bitmap: Bitmap,
    val corners: List<PointF> = emptyList(),
    val filter: ScanFilter = ScanFilter.Original
)

enum class ScanFilter { Original, BnW, Enhanced }
