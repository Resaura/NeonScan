package com.neonscan.ui.options

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.util.FileManager
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class OptionsViewModel(
    private val repo: DocumentRepository,
    private val fileManager: FileManager
) : ViewModel() {
    var title by mutableStateOf(defaultName())
    var exportPdf by mutableStateOf(true)
    var exportJpg by mutableStateOf(false)

    fun save(bitmap: Bitmap, onSaved: (DocumentEntity) -> Unit) {
        viewModelScope.launch {
            val id = UUID.randomUUID().toString()
            val pdfFile = if (exportPdf) repo.exportToPdf(bitmap, fileManager.newFile("${id}_p1", "pdf")) else null
            val jpgFile = if (exportJpg) repo.exportToJpg(bitmap, fileManager.newFile("${id}_p1", "jpg")) else null
            val main = pdfFile ?: jpgFile
            val entity = DocumentEntity(
                id = id,
                title = title,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                primaryFormat = if (pdfFile != null) DocumentFormat.PDF else DocumentFormat.JPG,
                filePathPrimary = requireNotNull(main).absolutePath,
                pageCount = 1
            )
            repo.saveDocument(entity)
            onSaved(entity)
        }
    }

    private fun defaultName(): String =
        "Scan_" + SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault()).format(Date())
}
