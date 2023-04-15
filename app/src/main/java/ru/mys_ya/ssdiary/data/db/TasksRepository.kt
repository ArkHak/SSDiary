package ru.mys_ya.ssdiary.data.db

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.ssdiary.data.Task

interface TasksRepository {
//    suspend fun getAllTasks(): List<Task>
    suspend fun getTasksList(timestamp: Long): List<Task>
    fun getTaskStream(id: Int): Flow<Task?>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}