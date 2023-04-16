package ru.mys_ya.ssdiary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mys_ya.ssdiary.data.TaskRepositoryImpl
import ru.mys_ya.ssdiary.data.db.TasksRepository
import ru.mys_ya.ssdiary.ui.screens.home.HomeViewModel
import ru.mys_ya.ssdiary.ui.screens.task.TaskViewModel

val appModule = module {

    viewModel {
        HomeViewModel(get())
    }

    viewModel{
        TaskViewModel(get())
    }

    single<TasksRepository> {
        TaskRepositoryImpl(get(), get())
    }
}