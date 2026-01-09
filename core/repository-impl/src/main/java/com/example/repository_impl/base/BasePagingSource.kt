package com.example.repository_impl.base

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<Value : Any>(
) : PagingSource<Int, Value>() {

    abstract suspend fun loadPage(
        page: Int,
        pageSize: Int
    ): List<Value>

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Value> {

        val page = params.key ?: 1

        return try {
            val data = loadPage(page, params.loadSize)

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Value>
    ): Int? = state.anchorPosition?.let { position ->
        state.closestPageToPosition(position)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
    }
}