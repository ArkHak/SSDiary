package ru.mys_ya.ssdiary.data.db

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.mys_ya.ssdiary.R
import ru.mys_ya.ssdiary.data.TaskFromJson
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity
import ru.mys_ya.ssdiary.data.mappers.TaskMapper
import java.io.InputStream

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class DiaryDatabase() : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: DiaryDatabase? = null

        fun getDatabase(
            context: Context,
            coroutineScope: CoroutineScope,
            mapper: TaskMapper,
        ): DiaryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DiaryDatabase::class.java,
                    context.getString(R.string.database_name)
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .apply {
                        coroutineScope.launch {
                            val listGson = getInitializationTasks(context)
                            val list = mapper.toEntityTask(listGson)
                            taskDao().insertList(list)
                        }
                    }
                    .also { Instance = it }
            }
        }
    }
}

private fun getInitializationTasks(context: Context): List<TaskFromJson> {
    val inputStream: InputStream = context.assets.open(context.getString(R.string.assets_file_json))
    val size: Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()

    val json = String(buffer, Charsets.UTF_8)

    val gson = Gson()
    val taskListType = object : TypeToken<List<TaskFromJson>>() {}.type
    return gson.fromJson(json, taskListType)
}
