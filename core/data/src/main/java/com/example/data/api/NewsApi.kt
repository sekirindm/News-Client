package com.example.data.api

import com.example.data.dto.NewsResponse

interface NewsApi {
    suspend fun todayNewsRequest(from: String, page: Int, pageSize: Int): NewsResponse
}