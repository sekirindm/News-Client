package com.example.data.api

import com.example.data.dto.NewsResponse

interface NewsApi {
    suspend fun popularityNewsRequest(page: Int, pageSize: Int): NewsResponse
    suspend fun latestNewsRequest(from: String, page: Int, pageSize: Int): NewsResponse
}