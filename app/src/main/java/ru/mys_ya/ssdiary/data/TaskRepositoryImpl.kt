package ru.mys_ya.ssdiary.data

import kotlinx.coroutines.flow.Flow
import ru.mys_ya.ssdiary.data.db.TaskDao
import ru.mys_ya.ssdiary.data.db.TasksRepository

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
) : TasksRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()
    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)
    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun deleteTask(task: Task) = taskDao.delete(task)
    override suspend fun updateTask(task: Task) = taskDao.update(task)
}
