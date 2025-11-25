package com.neonscan.ui.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.util.FileManager

class OptionsViewModelFactory(
    private val repo: DocumentRepository,
    private val fileManager: FileManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OptionsViewModel(repo, fileManager) as T
    }
}
