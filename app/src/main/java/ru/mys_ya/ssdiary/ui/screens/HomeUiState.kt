package ru.mys_ya.ssdiary.ui.screens

import ru.mys_ya.ssdiary.data.Task

sealed interface HomeUiState {
    data class Success(val tasks: List<Task>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}