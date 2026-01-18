package com.example.repository_api.home

import androidx.paging.PagingData
import com.example.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
   suspend fun latestNewsRequest(from: String): Flow<PagingData<NewsEntity>>
   suspend fun popularityNewsRequest(): Flow<PagingData<NewsEntity>>
   suspend fun getNewsList(type: String): List<NewsEntity>
}