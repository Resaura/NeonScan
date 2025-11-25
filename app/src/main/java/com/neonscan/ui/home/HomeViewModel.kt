package com.neonscan.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.repo.DocumentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: DocumentRepository) : ViewModel() {
    val docs = repo.observeDocuments()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun delete(doc: DocumentEntity) = viewModelScope.launch { repo.deleteDocument(doc) }

    fun rename(doc: DocumentEntity, newTitle: String) = viewModelScope.launch {
        repo.saveDocument(doc.copy(title = newTitle, updatedAt = System.currentTimeMillis()))
    }
}
