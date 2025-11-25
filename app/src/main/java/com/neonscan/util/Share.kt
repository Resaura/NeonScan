package com.neonscan.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun shareFile(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    val mime = when (file.extension.lowercase()) {
        "pdf" -> "application/pdf"
        "jpg", "jpeg", "png" -> "image/*"
        else -> "*/*"
    }
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = mime
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, "Scan partagé depuis NeonScan")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        clipData = android.content.ClipData.newRawUri("NeonScan", uri)
    }
    val chooser = Intent.createChooser(shareIntent, "Partager le document").apply {
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(chooser)
}
