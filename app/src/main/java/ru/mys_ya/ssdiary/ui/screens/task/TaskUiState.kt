package ru.mys_ya.ssdiary.ui.screens.task

import ru.mys_ya.ssdiary.data.Task

sealed interface TaskUiState {
    data class Success(val tasks: Task) : TaskUiState
    object Error : TaskUiState
    object Loading : TaskUiState
}