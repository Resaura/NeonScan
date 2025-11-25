package com.neonscan.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.neonscan.data.model.ScanFilter

object ImageFilters {
    fun apply(filter: ScanFilter, bitmap: Bitmap): Bitmap = when (filter) {
        ScanFilter.Original -> bitmap
        ScanFilter.BnW -> toBlackAndWhite(bitmap)
        ScanFilter.Enhanced -> enhance(bitmap)
    }

    private fun toBlackAndWhite(bitmap: Bitmap): Bitmap {
        val cm = ColorMatrix().apply { setSaturation(0f) }
        val paint = Paint().apply { colorFilter = ColorMatrixColorFilter(cm) }
        val out = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        Canvas(out).drawBitmap(bitmap, 0f, 0f, paint)
        return out
    }

    private fun enhance(bitmap: Bitmap): Bitmap {
        val cm = ColorMatrix(
            floatArrayOf(
                1.2f, 0f, 0f, 0f, 10f,
                0f, 1.2f, 0f, 0f, 10f,
                0f, 0f, 1.2f, 0f, 10f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        val paint = Paint().apply { colorFilter = ColorMatrixColorFilter(cm) }
        val out = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        Canvas(out).drawBitmap(bitmap, 0f, 0f, paint)
        return out
    }
}
