package com.neonscan.util

import android.content.Context
import android.graphics.Bitmap
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class PdfGenerator(@Suppress("unused") private val context: Context) {
    suspend fun saveSinglePage(bitmap: Bitmap, dest: File): File = withContext(Dispatchers.IO) {
        val doc = Document(PageSize.A4)
        PdfWriter.getInstance(doc, FileOutputStream(dest))
        doc.open()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)
        val image = Image.getInstance(stream.toByteArray())
        image.scaleToFit(PageSize.A4.width, PageSize.A4.height)
        image.alignment = Image.ALIGN_CENTER
        doc.add(image)
        doc.close()
        dest
    }
}
