package com.example.repository_api.home

import androidx.paging.PagingData
import com.example.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
   suspend fun todayNewsRequest(from: String): Flow<PagingData<NewsEntity>>
    suspend fun getNewsList(): List<NewsEntity>
}