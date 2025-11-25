package com.neonscan.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.camera.core.ImageProxy

fun Bitmap.rotate90(): Bitmap {
    val matrix = Matrix().apply { postRotate(90f) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun ImageProxy.toBitmap(): Bitmap? {
    val plane = planes.firstOrNull() ?: return null
    val buffer = plane.buffer
    val bytes = ByteArray(buffer.remaining())
    buffer.get(bytes)
    var bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    if (imageInfo.rotationDegrees != 0) {
        val matrix = Matrix().apply { postRotate(imageInfo.rotationDegrees.toFloat()) }
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, true)
    }
    return bmp
}
