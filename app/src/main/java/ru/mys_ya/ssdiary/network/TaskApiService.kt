package ru.mys_ya.ssdiary.network

import retrofit2.http.GET
import ru.mys_ya.ssdiary.data.Task

interface TaskApiService {
    @GET("start_data.json")
    suspend fun getLocalTasks(): List<Task>
}