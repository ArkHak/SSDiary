package ru.mys_ya.ssdiary.data.mappers

import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.data.TaskFromJson
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity

interface ITaskMapper {
    fun toDataTask(data: TaskEntity): Task
    fun toDataTask(data: TaskFromJson): Task
    fun toDataTask(dataList: List<TaskEntity>): List<Task>
    fun toEntityTask(data: TaskFromJson): TaskEntity
    fun toEntityTask(data: Task): TaskEntity
    fun toEntityTask(dataList: List<TaskFromJson>): List<TaskEntity>
}