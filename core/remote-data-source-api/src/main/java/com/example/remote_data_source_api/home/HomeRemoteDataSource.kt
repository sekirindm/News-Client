package com.example.remote_data_source_api.home

import com.example.data.base.RequestResult
import com.example.data.dto.NewsResponse

interface HomeRemoteDataSource {

    suspend fun getTodayNewsList(from: String) : RequestResult<NewsResponse>
}