package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.ssdiary.data.db.TasksRepository
import java.io.IOException

class TaskDetailViewModel(
    private val taskRepository: TasksRepository,
) : ViewModel() {

    var taskDetailUiState: TaskDetailUiState by mutableStateOf(TaskDetailUiState.Loading)
        private set

    fun getTask(taskId: Int) {
        viewModelScope.launch {
            taskDetailUiState = TaskDetailUiState.Loading
            taskDetailUiState = try {
                TaskDetailUiState.Success(taskRepository.getTask(taskId))
            } catch (e: IOException) {
                TaskDetailUiState.Error
            } catch (e: Exception) {
                TaskDetailUiState.Error
            }
        }
    }
}