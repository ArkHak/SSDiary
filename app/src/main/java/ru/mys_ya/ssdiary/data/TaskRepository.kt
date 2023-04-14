package ru.mys_ya.ssdiary.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

interface TaskRepository {
    suspend fun getLocalTask(): List<Task>
}

class LocalTaskRepository(
    private val gson: Gson,
    private val inputStream: InputStream,
) : TaskRepository {
    override suspend fun getLocalTask(): List<Task> {
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)

        val taskListType = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(json, taskListType)
    }
}
