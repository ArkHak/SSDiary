package ru.mys_ya.ssdiary.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.data.TaskRepository
import ru.mys_ya.ssdiary.data.TaskUiState
import java.io.IOException


sealed interface TasksUiState {
    data class Success(val tasks: List<Task>) : TasksUiState
    object Error : TasksUiState
    object Loading : TasksUiState
}

class SSDiaryViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    var tasksUiState: TasksUiState by mutableStateOf(TasksUiState.Loading)
        private set

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            tasksUiState = TasksUiState.Loading
            tasksUiState = try {
                TasksUiState.Success(taskRepository.getLocalTask())
            } catch (e: IOException) {
                TasksUiState.Error
            } catch (e: HttpException) {
                TasksUiState.Error
            }
        }
    }

    fun setTaskId(taskId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                id = taskId
            )
        }
    }
}