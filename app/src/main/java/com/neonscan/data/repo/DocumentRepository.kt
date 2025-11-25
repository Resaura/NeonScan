package com.neonscan.data.repo

import android.graphics.Bitmap
import com.neonscan.data.local.DocumentEntity
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DocumentRepository {
    fun observeDocuments(): Flow<List<DocumentEntity>>
    suspend fun getDocument(id: String): DocumentEntity?
    suspend fun saveDocument(entity: DocumentEntity)
    suspend fun deleteDocument(entity: DocumentEntity)
    suspend fun exportToPdf(bitmap: Bitmap, dest: File): File
    suspend fun exportToJpg(bitmap: Bitmap, dest: File): File
}
