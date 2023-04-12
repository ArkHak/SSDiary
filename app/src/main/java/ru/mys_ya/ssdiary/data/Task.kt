package ru.mys_ya.ssdiary.data

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateEnd: Long,
    val name: String,
    val description: String,
)