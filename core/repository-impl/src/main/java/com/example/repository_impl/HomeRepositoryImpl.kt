package com.example.repository_impl

import com.example.data.base.RequestResult
import com.example.database.dao.NewsDao
import com.example.mapper.home.NewsListDataToDomainMapper
import com.example.model.base.LocalResult
import com.example.model.base.ResponseError
import com.example.model.entity.NewsEntity
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.repository_api.home.HomeRepository

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val mapper: NewsListDataToDomainMapper,
    private val dao: NewsDao,
) : HomeRepository {

    override suspend fun todayNewsRequest(from: String) =
        when (val response = remoteDataSource.getTodayNewsList(from)) {
            is RequestResult.Success -> {
                val output = mapper.map(response.data.articles)
                dao.insertAll(output)
                LocalResult.Success(output)
            }
            is RequestResult.Warning -> LocalResult.Error(ResponseError(response.info))
            is RequestResult.Error -> LocalResult.Error(response.throwable as Exception)
        }

    override suspend fun getNewsList(): List<NewsEntity> {
        return dao.getNewsList()
    }
}