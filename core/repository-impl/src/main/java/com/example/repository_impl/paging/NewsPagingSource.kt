package com.example.repository_impl.paging

import com.example.data.base.RequestResult
import com.example.database.dao.NewsDao
import com.example.mapper.home.NewsListDataToDomainMapper
import com.example.model.entity.NewsEntity
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.repository_impl.base.BasePagingSource

class NewsPagingSource(
    private val from: String,
    private val remoteDataSource: HomeRemoteDataSource,
    private val mapper: NewsListDataToDomainMapper,
    private val dao: NewsDao,
) : BasePagingSource<NewsEntity>() {

    override suspend fun loadPage(
        page: Int,
        pageSize: Int
    ): List<NewsEntity> {
        return when (
            val result = remoteDataSource.getTodayNewsList(from, page, pageSize)
        ) {
            is RequestResult.Success -> {
                val output = mapper.map(result.data.articles)
                dao.clear()
                dao.insertAll(output)
                dao.getNewsList()
            }
            is RequestResult.Error -> throw result.throwable
            is RequestResult.Warning -> emptyList()
        }
    }
}