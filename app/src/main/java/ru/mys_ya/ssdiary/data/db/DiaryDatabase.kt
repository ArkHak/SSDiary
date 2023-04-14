package ru.mys_ya.ssdiary.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mys_ya.ssdiary.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: DiaryDatabase? = null

        fun getDatabase(context: Context): DiaryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DiaryDatabase::class.java,
                    "task_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}