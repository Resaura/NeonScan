package com.neonscan.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.neonscan.data.model.ScanFilter

class EditViewModel : ViewModel() {
    var filter by mutableStateOf(ScanFilter.Original)
    var rotated by mutableStateOf(0)

    fun rotate() {
        rotated = (rotated + 90) % 360
    }
}
