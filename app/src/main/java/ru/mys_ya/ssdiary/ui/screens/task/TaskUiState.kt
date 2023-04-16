package ru.mys_ya.ssdiary.ui.screens.task

import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.util.SECONDS_IN_DAY

data class TaskUiState(
    val id: Int = 0,
    val dateStart: Long = 1681671507,
    val dateFinish: Long = 0L,
    val name: String = "",
    val description: String = "",
    val actionEnabled: Boolean = false,
)

fun TaskUiState.toTask(): Task = Task(
    id = id,
    dateStart = dateStart,
    dateFinish = dateStart + SECONDS_IN_DAY,
    name = name,
    description = description
)

fun TaskUiState.isValid(): Boolean {
    return name.isNotBlank() && description.isNotBlank() && dateStart != 0L
}