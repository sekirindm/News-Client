package com.example.repository_impl.paging

import android.util.Log
import com.example.data.base.RequestResult
import com.example.database.dao.NewsDao
import com.example.mapper.home.NewsCarouselListDataToDomainMapper
import com.example.mapper.home.NewsMainListDataToDomainMapper
import com.example.model.entity.NewsEntity
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.repository_impl.base.BasePagingSource
import com.example.utils.BusinessConstants

class CarouselPagingSource(
    private val from: String,
    private val remoteDataSource: HomeRemoteDataSource,
    private val mapper: NewsCarouselListDataToDomainMapper,
    private val dao: NewsDao,
) : BasePagingSource<NewsEntity>() {

    override suspend fun loadPage(
        page: Int,
        pageSize: Int
    ): List<NewsEntity> {
        return when (
            val result = remoteDataSource.latestNewsRequest(from, page, pageSize)
        ) {
            is RequestResult.Success -> {
                val output = mapper.map(result.data.articles)
                dao.clear()
                dao.insertAll(output)
                dao.getNewsList(BusinessConstants.NEWS_TYPE_CAROUSEL)
            }
            is RequestResult.Error -> throw result.throwable
            is RequestResult.Warning -> emptyList()
        }
    }
}