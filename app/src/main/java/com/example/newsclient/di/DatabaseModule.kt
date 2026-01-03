package com.example.newsclient.di

import androidx.room.Room
import com.example.database.AppDatabase
import com.example.database.dao.NewsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<NewsDao> {
        get<AppDatabase>().newsDao()
    }
}
