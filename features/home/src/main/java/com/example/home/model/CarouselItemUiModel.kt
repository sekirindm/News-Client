package com.example.home.model

data class CarouselItemUiModel(
    val id: Long,
    val imageUrl: String?,
    val sourceName: String,
    val authorName: String?,
    val title: String,
    val content: String,
    val date: String
)