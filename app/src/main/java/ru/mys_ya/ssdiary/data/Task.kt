package ru.mys_ya.ssdiary.data

import com.google.gson.annotations.SerializedName

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
)

data class TaskFromJson(
    val id: Int,
    @SerializedName("date_start") val dateStart: Long,
    @SerializedName("date_finish") val dateFinish: Long,
    val name: String,
    val description: String,
)