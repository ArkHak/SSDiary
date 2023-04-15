package ru.mys_ya.ssdiary.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity

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
    fun getTask(id: Int): Flow<TaskEntity>

    @Query("SELECT * FROM task ORDER BY date_start ASC")
    fun getAllTasks(): Flow<List<TaskEntity>>
}