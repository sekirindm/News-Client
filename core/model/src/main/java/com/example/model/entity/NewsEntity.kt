package com.example.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.base.Abstract

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val author: String?,
    val sourceName: String,
    val imageUrl: String?,
    val publishedAt: String,
    val isRead: Boolean = false,
    val type: String,
) : Abstract.DomainObject