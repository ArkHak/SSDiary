package ru.mys_ya.ssdiary.data.db

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.ssdiary.data.Task

interface TasksRepository {
    fun getAllTasksStream(): Flow<List<Task>>
    fun getTaskStream(id: Int): Flow<Task?>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}