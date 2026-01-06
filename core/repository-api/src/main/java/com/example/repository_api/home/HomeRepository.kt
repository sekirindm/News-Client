package com.example.repository_api.home

import com.example.model.base.LocalResult
import com.example.model.entity.NewsEntity

interface HomeRepository {
   suspend fun todayNewsRequest(from: String): LocalResult<List<NewsEntity>>
   suspend fun getNewsList(): List<NewsEntity>
}