package ru.mys_ya.ssdiary.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.mys_ya.ssdiary.data.TaskUiState

class SSDiaryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun setTaskId(taskId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                id = taskId
            )
        }
    }

}