package com.example.data.dto

import com.example.data.base.BaseResponse
import com.example.model.base.Abstract
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles: List<ArticleResponse>
) : BaseResponse()

@Serializable
data class ArticleResponse(
    val source: SourceResponse,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String
) : Abstract.DataObject

@Serializable
data class SourceResponse(
    val id: String? = null,
    val name: String
): Abstract.DataObject
