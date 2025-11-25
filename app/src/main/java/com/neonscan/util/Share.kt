package com.neonscan.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun shareFile(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = if (file.extension == "pdf") "application/pdf" else "image/jpeg"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Partager le document"))
}
