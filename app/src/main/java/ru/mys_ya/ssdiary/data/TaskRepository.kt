package ru.mys_ya.ssdiary.data

import ru.mys_ya.ssdiary.network.TaskApiService

interface TaskRepository {
    suspend fun getLocalTask(): List<Task>
}

class LocalTaskRepository(
    private val taskApiService: TaskApiService,
) : TaskRepository {
    override suspend fun getLocalTask(): List<Task> = taskApiService.getLocalTasks()
}

