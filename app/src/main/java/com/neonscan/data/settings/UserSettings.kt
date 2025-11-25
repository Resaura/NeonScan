package com.neonscan.data.settings

import com.neonscan.data.model.DocumentFormat

enum class ScanQuality { LOW, STANDARD, HIGH }

data class UserSettings(
    val quality: ScanQuality = ScanQuality.STANDARD,
    val defaultFormat: DocumentFormat = DocumentFormat.PDF,
    val interstitialFrequency: Int = 3
)
