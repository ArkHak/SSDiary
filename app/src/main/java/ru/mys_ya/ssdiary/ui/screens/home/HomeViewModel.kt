package ru.mys_ya.ssdiary.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mys_ya.ssdiary.data.db.TasksRepository
import java.io.IOException

class HomeViewModel(
    private val taskRepository: TasksRepository,
) : ViewModel() {
    private var timestampFlag: Long = 0L

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    fun getTaskList(timestamp: Long) {
        if (timestamp != timestampFlag) {
            viewModelScope.launch {
                homeUiState = HomeUiState.Loading
                homeUiState = try {
                    HomeUiState.Success(taskRepository.getTasksList(timestamp))
                } catch (e: IOException) {
                    HomeUiState.Error
                } catch (e: Exception) {
                    HomeUiState.Error
                }
            }
        }
        timestampFlag = timestamp
    }
}