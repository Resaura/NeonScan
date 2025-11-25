package com.neonscan.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonscan.data.model.DocumentFormat
import com.neonscan.data.settings.ScanQuality
import com.neonscan.data.settings.SettingsRepository
import com.neonscan.data.settings.UserSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val repo: SettingsRepository) : ViewModel() {
    val settings: StateFlow<UserSettings> = repo.settings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UserSettings()
    )

    fun setQuality(q: ScanQuality) = viewModelScope.launch { repo.updateQuality(q) }
    fun setFormat(f: DocumentFormat) = viewModelScope.launch { repo.updateFormat(f) }
    fun setFrequency(freq: Int) = viewModelScope.launch { repo.updateInterstitialFrequency(freq) }
}
