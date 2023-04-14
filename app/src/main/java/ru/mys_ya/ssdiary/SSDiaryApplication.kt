package ru.mys_ya.ssdiary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.mys_ya.ssdiary.di.appModule

class SSDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SSDiaryApplication)
            modules(appModule)
        }
    }
}