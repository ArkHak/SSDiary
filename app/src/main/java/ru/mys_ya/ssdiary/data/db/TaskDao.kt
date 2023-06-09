package ru.mys_ya.ssdiary.data.db

import androidx.room.*
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity
import ru.mys_ya.ssdiary.util.SECONDS_IN_DAY

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(tasksList: List<TaskEntity>)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTask(id: Int): TaskEntity

    @Query("SELECT * FROM task WHERE date_start BETWEEN :timestamp and :timestamp+${SECONDS_IN_DAY} ORDER BY date_start ASC")
    suspend fun getAllTasks(timestamp: Long): List<TaskEntity>
}
