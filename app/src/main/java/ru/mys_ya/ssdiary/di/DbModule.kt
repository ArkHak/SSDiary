package ru.mys_ya.ssdiary.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.mys_ya.ssdiary.data.db.DiaryDatabase
import ru.mys_ya.ssdiary.data.db.TaskDao

val dbModule = module {
    single<TaskDao> {
        DiaryDatabase.getDatabase(androidApplication()).taskDao()
    }
}