package ru.mys_ya.ssdiary.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.mys_ya.ssdiary.data.db.DiaryDatabase
import ru.mys_ya.ssdiary.data.db.TaskDao
import ru.mys_ya.ssdiary.data.mappers.TaskMapper

val dbModule = module {

    single<TaskDao> {
        DiaryDatabase.getDatabase(androidApplication(), get(), get()).taskDao()
    }

    single {
        CoroutineScope(Dispatchers.IO + Job())
    }

    single {
        TaskMapper()
    }
}