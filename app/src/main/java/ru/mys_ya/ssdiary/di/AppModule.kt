package ru.mys_ya.ssdiary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mys_ya.ssdiary.data.TaskRepositoryImpl
import ru.mys_ya.ssdiary.data.db.TasksRepository
import ru.mys_ya.ssdiary.ui.screens.SSDiaryViewModel

val appModule = module {

    viewModel { SSDiaryViewModel(get()) }

    single<TasksRepository> {
        TaskRepositoryImpl(get())
    }
}