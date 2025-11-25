package com.neonscan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neonscan.data.model.DocumentFormat
import java.util.UUID

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val primaryFormat: DocumentFormat,
    val filePathPrimary: String,
    val pageCount: Int
)
