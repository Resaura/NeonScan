package com.neonscan.domain.usecase

import com.neonscan.data.local.DocumentEntity
import com.neonscan.data.repo.DocumentRepository

class DeleteDocumentUseCase(private val repo: DocumentRepository) {
    suspend operator fun invoke(entity: DocumentEntity) {
        repo.deleteDocument(entity)
    }
}
