package com.neonscan.domain.usecase

import android.graphics.Bitmap
import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.util.FileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class SaveScanUseCase(
    private val repo: DocumentRepository,
    private val fileManager: FileManager
) {
    suspend operator fun invoke(
        bitmap: Bitmap,
        exportPdf: Boolean,
        exportJpg: Boolean,
        title: String? = null
    ): DocumentEntity = withContext(Dispatchers.IO) {
        val id = UUID.randomUUID().toString()
        val baseName = title ?: defaultName()
        val pdfFile = if (exportPdf) repo.exportToPdf(bitmap, fileManager.newFile("${id}_p1", "pdf")) else null
        val jpgFile = if (exportJpg) repo.exportToJpg(bitmap, fileManager.newFile("${id}_p1", "jpg")) else null
        val mainFile = pdfFile ?: jpgFile
        val entity = DocumentEntity(
            id = id,
            title = baseName,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            primaryFormat = if (pdfFile != null) DocumentFormat.PDF else DocumentFormat.JPG,
            filePathPrimary = requireNotNull(mainFile).absolutePath,
            pageCount = 1
        )
        repo.saveDocument(entity)
        entity
    }

    private fun defaultName(): String =
        "Scan_" + SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault()).format(Date())
}
