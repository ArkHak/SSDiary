package ru.mys_ya.ssdiary.di

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val sharedPrefModule = module {
    val appSettings = "appSettings"

    single {
        androidApplication().getSharedPreferences(appSettings, Context.MODE_PRIVATE)
    }
}