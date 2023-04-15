package ru.mys_ya.ssdiary.data.db.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("date_start") val dateStart: Long,
    @ColumnInfo("date_finish") val dateEnd: Long,
    val name: String,
    val description: String,
)