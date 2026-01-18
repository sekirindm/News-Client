package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.NewsDao
import com.example.model.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val DB_NAME = "app_database.db"
    }
}
