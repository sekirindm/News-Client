package com.example.remote_data_source_api.home

import com.example.data.base.RequestResult
import com.example.data.dto.NewsResponse

interface HomeRemoteDataSource {
    suspend fun popularityNewsRequest(page: Int, pageSize: Int) : RequestResult<NewsResponse>
    suspend fun latestNewsRequest(from: String, page: Int, pageSize: Int) : RequestResult<NewsResponse>
}