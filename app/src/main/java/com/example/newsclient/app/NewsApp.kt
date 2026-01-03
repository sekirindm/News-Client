package com.example.newsclient.app

import android.app.Application
import com.example.newsclient.di.appModule
import com.example.newsclient.di.databaseModule
import com.example.newsclient.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NewsApp)
            modules(buildModuleList())
        }
    }

    private fun buildModuleList() : List<Module> {
        return networkModule + appModule + databaseModule
    }
}
