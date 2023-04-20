package ru.mys_ya.ssdiary.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SSDiaryScreenViewModel : ViewModel() {

    var uiState: SSDiaryScreenUiState by mutableStateOf(SSDiaryScreenUiState())
        private set

    fun changeFabEnable(isFabEnable: Boolean) {
        uiState = uiState.copy(isFabEnable = isFabEnable)
    }
}