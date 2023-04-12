package ru.mys_ya.ssdiary.data

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateEnd: Long,
    val name: String,
    val description: String,
)

val simpleTask = Task(
    id = 1,
    dateStart = 1681318800,
    dateEnd = 1681322400,
    name = "Simple Task",
    description = "Description Simple Task"
)