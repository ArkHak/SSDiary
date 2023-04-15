package ru.mys_ya.ssdiary.data.mappers

import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.data.TaskFromJson
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity

class TaskMapper : ITaskMapper {
    override fun toDataTask(data: TaskEntity): Task =
        Task(
            id = data.id,
            dateStart = data.dateStart,
            dateEnd = data.dateEnd,
            name = data.name,
            description = data.description
        )


    override fun toDataTask(data: TaskFromJson): Task =
        Task(
            id = data.id,
            dateStart = data.dateStart,
            dateEnd = data.dateEnd,
            name = data.name,
            description = data.description
        )

    override fun toDataTask(dataList: List<TaskEntity>): List<Task> =
        dataList.map { task ->
            toDataTask(task)
        }

    override fun toEntityTask(data: TaskFromJson): TaskEntity =
        TaskEntity(
            id = data.id,
            dateStart = data.dateStart,
            dateEnd = data.dateEnd,
            name = data.name,
            description = data.description
        )

    override fun toEntityTask(data: Task): TaskEntity =
        TaskEntity(
            id = data.id,
            dateStart = data.dateStart,
            dateEnd = data.dateEnd,
            name = data.name,
            description = data.description
        )

    override fun toEntityTask(dataList: List<TaskFromJson>): List<TaskEntity> =
        dataList.map { task ->
            toEntityTask(task)
        }
}