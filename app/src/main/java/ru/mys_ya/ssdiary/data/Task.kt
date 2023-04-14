package ru.mys_ya.ssdiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("date_start") @SerializedName("date_start") val dateStart: Long,
    @ColumnInfo("date_finish") @SerializedName("date_finish") val dateEnd: Long,
    val name: String,
    val description: String,
)