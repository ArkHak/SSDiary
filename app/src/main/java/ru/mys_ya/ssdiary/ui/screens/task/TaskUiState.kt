package ru.mys_ya.ssdiary.ui.screens.task

import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.util.SECONDS_IN_DAY
import ru.mys_ya.ssdiary.util.convertSystemLocalTimeDateToTimestamp
import java.time.LocalDate
import java.time.LocalTime

data class TaskUiState(
    val id: Int = 0,
    val timeStart: LocalTime = LocalTime.now(),
    val dateStart: LocalDate = LocalDate.now(),
    val name: String = "",
    val description: String = "",
    val actionEnabled: Boolean = false,
)

fun TaskUiState.toTask(): Task = Task(
    id = id,
    dateStart = convertSystemLocalTimeDateToTimestamp(timeStart, dateStart),
    dateFinish = convertSystemLocalTimeDateToTimestamp(timeStart, dateStart) + SECONDS_IN_DAY,
    name = name,
    description = description
)

fun TaskUiState.isValid(): Boolean {
    return name.isNotBlank() && description.isNotBlank()
}