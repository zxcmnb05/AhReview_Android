package com.hackathon.ahreview

import android.app.Application
import com.hackathon.ahreview.module.RepositoryModule
import com.hackathon.ahreview.module.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@Application)
            val modules = listOf(ViewModelModule, RepositoryModule)

            modules(modules)
        }
    }
}