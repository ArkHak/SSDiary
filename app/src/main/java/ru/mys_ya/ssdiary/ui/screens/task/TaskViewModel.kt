package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.ssdiary.data.db.TasksRepository
import java.io.IOException

class TaskViewModel(
    private val taskRepository: TasksRepository,
) : ViewModel() {

    var taskUiState: TaskUiState by mutableStateOf(TaskUiState.Loading)
        private set

    fun getTask(taskId: Int) {
        viewModelScope.launch {
            taskUiState = TaskUiState.Loading
            taskUiState = try {
                TaskUiState.Success(taskRepository.getTask(taskId))
            } catch (e: IOException) {
                TaskUiState.Error
            } catch (e: Exception) {
                TaskUiState.Error
            }
        }
    }
}