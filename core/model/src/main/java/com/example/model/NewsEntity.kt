package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val author: String,
    val sourceName: String,
    val imageUrl: String?,
    val publishedAt: String,
    val isRead: Boolean
)