package com.neonscan.data.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.neonscan.data.model.DocumentFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore(name = "neonscan_settings")

class SettingsRepository(private val context: Context) {

    private val KEY_QUALITY = stringPreferencesKey("quality")
    private val KEY_FORMAT = stringPreferencesKey("default_format")
    private val KEY_FREQ = intPreferencesKey("interstitial_frequency")

    val settings: Flow<UserSettings> = context.settingsDataStore.data.map { prefs ->
        UserSettings(
            quality = prefs[KEY_QUALITY]?.let { runCatching { ScanQuality.valueOf(it) }.getOrNull() } ?: ScanQuality.STANDARD,
            defaultFormat = prefs[KEY_FORMAT]?.let { runCatching { DocumentFormat.valueOf(it) }.getOrNull() } ?: DocumentFormat.PDF,
            interstitialFrequency = prefs[KEY_FREQ] ?: 3
        )
    }

    suspend fun updateQuality(quality: ScanQuality) {
        context.settingsDataStore.edit { it[KEY_QUALITY] = quality.name }
    }

    suspend fun updateFormat(format: DocumentFormat) {
        context.settingsDataStore.edit { it[KEY_FORMAT] = format.name }
    }

    suspend fun updateInterstitialFrequency(freq: Int) {
        context.settingsDataStore.edit { it[KEY_FREQ] = freq }
    }
}
