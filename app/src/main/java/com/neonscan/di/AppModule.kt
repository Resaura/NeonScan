package com.neonscan.di

import android.content.Context
import androidx.room.Room
import com.neonscan.data.local.AppDatabase
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.data.repo.LocalDocumentRepository
import com.neonscan.data.settings.SettingsRepository
import com.neonscan.util.FileManager
import com.neonscan.util.PdfGenerator

object AppModule {
    fun provideDb(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "neonscan.db").build()

    fun provideRepo(context: Context): DocumentRepository {
        val db = provideDb(context)
        val fileManager = FileManager(context)
        val pdfGenerator = PdfGenerator(context)
        return LocalDocumentRepository(db.documentDao(), fileManager, pdfGenerator)
    }

    fun provideSettings(context: Context): SettingsRepository = SettingsRepository(context)
}
