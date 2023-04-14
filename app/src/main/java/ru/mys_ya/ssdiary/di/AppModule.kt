package ru.mys_ya.ssdiary.di

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mys_ya.ssdiary.data.LocalTaskRepository
import ru.mys_ya.ssdiary.data.TaskRepository
import ru.mys_ya.ssdiary.network.TaskApiService
import ru.mys_ya.ssdiary.ui.SSDiaryViewModel

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("file:///android_asset/start_data.json")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single<TaskApiService> {
        get<Retrofit>().create(TaskApiService::class.java)
    }

    single<TaskRepository> {
        LocalTaskRepository(get())
    }

    viewModel { SSDiaryViewModel(get()) }
}