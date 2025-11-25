package com.neonscan.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neonscan.data.repo.DocumentRepository

class DetailViewModelFactory(
    private val repo: DocumentRepository,
    private val id: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repo, id) as T
    }
}
