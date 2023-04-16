package ru.mys_ya.ssdiary.ui.screens.task

import ru.mys_ya.ssdiary.data.Task

sealed interface TaskDetailUiState {
    data class Success(val tasks: Task) : TaskDetailUiState
    object Error : TaskDetailUiState
    object Loading : TaskDetailUiState
}