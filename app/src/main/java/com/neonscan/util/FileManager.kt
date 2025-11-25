package com.neonscan.util

import android.content.Context
import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class FileManager(private val context: Context) {
    private fun scansDir(): File = File(context.filesDir, "scans").apply { mkdirs() }

    fun newFile(name: String, ext: String): File = File(scansDir(), "$name.$ext")

    suspend fun saveJpg(bitmap: Bitmap, dest: File): File = withContext(Dispatchers.IO) {
        dest.outputStream().use { bitmap.compress(Bitmap.CompressFormat.JPEG, 92, it) }
        dest
    }

    fun deleteFile(path: String) {
        runCatching { File(path).delete() }
    }
}
