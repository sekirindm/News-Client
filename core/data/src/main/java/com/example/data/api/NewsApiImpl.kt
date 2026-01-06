package com.example.data.api

import com.example.data.dto.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApiImpl(
    private val client: HttpClient,
    private val apiKey: String
) : NewsApi {

    override suspend fun todayNewsRequest(from: String): NewsResponse {
        return client.get("everything") {
            parameter("q", "news")
            parameter("from", from)
            parameter("apiKey", apiKey)
        }.body()
    }
}