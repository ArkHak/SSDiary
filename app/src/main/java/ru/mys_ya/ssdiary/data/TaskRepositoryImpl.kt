package ru.mys_ya.ssdiary.data

import ru.mys_ya.ssdiary.data.db.TaskDao
import ru.mys_ya.ssdiary.data.db.TasksRepository
import ru.mys_ya.ssdiary.data.mappers.TaskMapper

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val mapper: TaskMapper,
) : TasksRepository {

    override suspend fun getTasksList(timestamp: Long): List<Task> =
        taskDao.getAllTasks(timestamp).map {
            mapper.toDataTask(it)
        }

    override suspend fun getTask(id: Int): Task =
        mapper.toDataTask(taskDao.getTask(id))

    override suspend fun insertTask(task: Task) = taskDao.insert(mapper.toEntityTask(task))
    override suspend fun deleteTask(task: Task) = taskDao.delete(mapper.toEntityTask(task))
    override suspend fun updateTask(task: Task) = taskDao.update(mapper.toEntityTask(task))
}
