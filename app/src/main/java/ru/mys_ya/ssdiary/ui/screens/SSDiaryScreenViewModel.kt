package ru.mys_ya.ssdiary.ui.screens

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SSDiaryScreenViewModel(
    private val sharedPrefs: SharedPreferences,
) : ViewModel() {

    var uiState: SSDiaryScreenUiState by mutableStateOf(
        SSDiaryScreenUiState(
            isTaskTableView = getSharedPreferences()
        )
    )
        private set

    fun changeFabEnable(isFabEnable: Boolean) {
        uiState = uiState.copy(isFabEnable = isFabEnable)
    }

    fun changeSettingsEnable(isSettingsEnable: Boolean) {
        uiState = uiState.copy(isSettingsEnable = isSettingsEnable)
    }

    private fun getSharedPreferences(): Boolean =
        sharedPrefs.getBoolean(TASK_VIEW, true)


    fun changeTasksView(tasksTableView: Boolean) {
        uiState = uiState.copy(isTaskTableView = tasksTableView)
        with(sharedPrefs.edit()) {
            putBoolean(TASK_VIEW, tasksTableView)
            apply()
        }
    }

    companion object {
        private const val TASK_VIEW = "TASK_VIEW"
    }
}