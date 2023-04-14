package ru.mys_ya.ssdiary.network

import ru.mys_ya.ssdiary.data.Task

interface TaskApiService {

    suspend fun getLocalTasks(): List<Task>
}