package com.example.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.database.dao.NewsDao
import com.example.mapper.home.NewsCarouselListDataToDomainMapper
import com.example.mapper.home.NewsMainListDataToDomainMapper
import com.example.model.entity.NewsEntity
import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.repository_api.home.HomeRepository
import com.example.repository_impl.paging.CarouselPagingSource
import com.example.repository_impl.paging.NewsPagingSource

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val newsMainMapper: NewsMainListDataToDomainMapper,
    private val newsCarouselMapper: NewsCarouselListDataToDomainMapper,
    private val dao: NewsDao,
) : HomeRepository {

    override suspend fun popularityNewsRequest() = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(
                remoteDataSource = remoteDataSource,
                mapper = newsMainMapper,
                dao = dao
            )
        }
    ).flow

    override suspend fun latestNewsRequest(from: String) = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10 ,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CarouselPagingSource(
                from = from,
                remoteDataSource = remoteDataSource,
                mapper = newsCarouselMapper,
                dao = dao
            )
        }
    ).flow

    override suspend fun getNewsList(type: String): List<NewsEntity> {
        return dao.getNewsList(type)
    }
}