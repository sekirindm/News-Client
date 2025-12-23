package com.example.data.api

import com.example.data.dto.NewsResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface NewsApi {
    suspend fun todayNewsRequest(from: String): NewsResponse
}