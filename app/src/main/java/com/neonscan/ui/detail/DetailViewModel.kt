package com.neonscan.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.repo.DocumentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class DetailViewModel(
    private val repo: DocumentRepository,
    private val id: String
) : ViewModel() {
    private val _state = MutableStateFlow<DocumentEntity?>(null)
    val state: StateFlow<DocumentEntity?> = _state

    init {
        viewModelScope.launch { _state.value = repo.getDocument(id) }
    }

    fun delete() = viewModelScope.launch {
        _state.value?.let { repo.deleteDocument(it) }
    }

    fun rename(newTitle: String) = viewModelScope.launch {
        _state.value?.let {
            val updated = it.copy(title = newTitle, updatedAt = System.currentTimeMillis())
            repo.saveDocument(updated)
            _state.value = updated
        }
    }

    fun convert() = viewModelScope.launch {
        val doc = _state.value ?: return@launch
        val bitmap = android.graphics.BitmapFactory.decodeFile(doc.filePathPrimary) ?: return@launch
        val newFile = if (doc.primaryFormat == DocumentFormat.JPG) {
            repo.exportToPdf(bitmap, File(doc.filePathPrimary.replace(".jpg", ".pdf")))
        } else {
            repo.exportToJpg(bitmap, File(doc.filePathPrimary.replace(".pdf", ".jpg")))
        }
        val updated = doc.copy(
            primaryFormat = if (doc.primaryFormat == DocumentFormat.JPG) DocumentFormat.PDF else DocumentFormat.JPG,
            filePathPrimary = newFile.absolutePath,
            updatedAt = System.currentTimeMillis()
        )
        repo.saveDocument(updated)
        _state.value = updated
    }
}
