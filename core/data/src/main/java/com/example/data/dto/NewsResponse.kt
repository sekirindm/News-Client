package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class NewsResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles: List<ArticleResponse>
)

@Serializable
data class ArticleResponse(
    val source: SourceResponse,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)

@Serializable
data class SourceResponse(
    val id: String? = null,
    val name: String
)
