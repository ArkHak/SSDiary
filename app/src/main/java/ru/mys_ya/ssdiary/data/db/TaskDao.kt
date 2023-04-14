package ru.mys_ya.ssdiary.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.mys_ya.ssdiary.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Query("SELECT * FROM task ORDER BY date_start ASC")
    fun getAllTasks(): Flow<List<Task>>
}