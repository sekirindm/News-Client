package com.example.remote_data_source_impl.home

import com.example.data.api.NewsApi
import com.example.data.base.BaseResponse
import com.example.data.base.RequestResult
import com.example.data.dto.NewsResponse
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.remote_data_source_impl.base.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers

class HomeRemoteDataSourceImpl(
   private val newsApi: NewsApi
) : HomeRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getTodayNewsList(from: String) : RequestResult<NewsResponse> {
        return safeApiCall(Dispatchers.IO) {
            newsApi.todayNewsRequest(from)
        }
    }
}