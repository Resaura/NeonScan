package com.neonscan.data.repo

import android.graphics.Bitmap
import com.neonscan.data.local.DocumentDao
import com.neonscan.data.local.DocumentEntity
import com.neonscan.util.FileManager
import com.neonscan.util.PdfGenerator
import kotlinx.coroutines.flow.Flow
import java.io.File

class LocalDocumentRepository(
    private val dao: DocumentDao,
    private val fileManager: FileManager,
    private val pdfGenerator: PdfGenerator
) : DocumentRepository {

    override fun observeDocuments(): Flow<List<DocumentEntity>> = dao.observeDocuments()

    override suspend fun getDocument(id: String): DocumentEntity? = dao.getById(id)

    override suspend fun saveDocument(entity: DocumentEntity) {
        dao.upsert(entity)
    }

    override suspend fun deleteDocument(entity: DocumentEntity) {
        dao.delete(entity)
        fileManager.deleteFile(entity.filePathPrimary)
    }

    override suspend fun exportToPdf(bitmap: Bitmap, dest: File): File =
        pdfGenerator.saveSinglePage(bitmap, dest)

    override suspend fun exportToJpg(bitmap: Bitmap, dest: File): File =
        fileManager.saveJpg(bitmap, dest)
}
