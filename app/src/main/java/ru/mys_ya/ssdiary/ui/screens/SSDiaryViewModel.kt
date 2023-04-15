package ru.mys_ya.ssdiary.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.data.db.TasksRepository

data class HomeUiState(val taskList: List<Task> = listOf())
class SSDiaryViewModel(
    taskRepository: TasksRepository,
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        taskRepository.getAllTasksStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}