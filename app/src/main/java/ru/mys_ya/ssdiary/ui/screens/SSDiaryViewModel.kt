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
    private val taskRepository: TasksRepository,
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

//    private val _uiState = MutableStateFlow(TaskUiState())
//    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()
////
//    var tasksUiState: TasksUiState by mutableStateOf(TasksUiState.Loading)
//        private set
//
//    init {
//        getTasks()
//    }
//
//    fun getTasks() {
//        viewModelScope.launch {
//            tasksUiState = TasksUiState.Loading
//            tasksUiState = try {
//                TasksUiState.Success(taskRepository.getLocalTask())
//            } catch (e: IOException) {
//                TasksUiState.Error
//            } catch (e: HttpException) {
//                TasksUiState.Error
//            }
//        }
//    }
//
//    fun setTaskId(taskId: String) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                id = taskId
//            )
//        }
//    }
}