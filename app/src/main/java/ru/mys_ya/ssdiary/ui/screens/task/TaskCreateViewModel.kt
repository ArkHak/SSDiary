package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.mys_ya.ssdiary.data.db.TasksRepository

class TaskCreateViewModel(
    private val taskRepository: TasksRepository,
) : ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(newTaskUiState: TaskUiState) {
        taskUiState = newTaskUiState.copy(actionEnabled = newTaskUiState.isValid())
    }

    suspend fun saveTask(){
        if (taskUiState.isValid()){
            taskRepository.insertTask(taskUiState.toTask())
        }
    }
}