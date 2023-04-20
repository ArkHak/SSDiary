package ru.mys_ya.ssdiary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.mys_ya.ssdiary.di.appModule
import ru.mys_ya.ssdiary.di.dbModule
import ru.mys_ya.ssdiary.di.gsonModule
import ru.mys_ya.ssdiary.di.sharedPref

class SSDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SSDiaryApplication)
            modules(
                appModule,
                gsonModule,
                dbModule,
                sharedPref
            )
        }
    }
}