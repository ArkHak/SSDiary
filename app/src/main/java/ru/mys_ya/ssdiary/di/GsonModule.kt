package ru.mys_ya.ssdiary.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.InputStream

val gsonModule = module {

    single<InputStream> {
        androidContext().assets.open("init_data.json")
    }

    single {
        Gson()
    }
}