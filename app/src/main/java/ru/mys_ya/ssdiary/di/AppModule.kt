package ru.mys_ya.ssdiary.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mys_ya.ssdiary.data.LocalTaskRepository
import ru.mys_ya.ssdiary.data.TaskRepository
import ru.mys_ya.ssdiary.ui.SSDiaryViewModel
import java.io.InputStream

val appModule = module {

    single<TaskRepository> {
        LocalTaskRepository(
            get(), get()
        )
    }

    single<InputStream> {
        androidContext().assets.open("start_data.json")
    }

    single {
        Gson()
    }

    viewModel { SSDiaryViewModel(get()) }
}