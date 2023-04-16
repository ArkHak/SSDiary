package ru.mys_ya.ssdiary.data.db

import ru.mys_ya.ssdiary.data.Task

interface TasksRepository {
    suspend fun getTasksList(timestamp: Long): List<Task>
    suspend fun getTask(id: Int): Task
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}