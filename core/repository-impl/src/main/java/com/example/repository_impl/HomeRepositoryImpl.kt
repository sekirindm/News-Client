package com.example.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.database.dao.NewsDao
import com.example.mapper.home.NewsListDataToDomainMapper
import com.example.model.entity.NewsEntity
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.repository_api.home.HomeRepository
import com.example.repository_impl.paging.NewsPagingSource

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val mapper: NewsListDataToDomainMapper,
    private val dao: NewsDao,
) : HomeRepository {

    override suspend fun todayNewsRequest(from: String) = Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10 ,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    from = from,
                    remoteDataSource = remoteDataSource,
                    mapper = mapper,
                    dao = dao
                )
            }
        ).flow

    override suspend fun getNewsList(): List<NewsEntity> {
        return dao.getNewsList()
    }
}