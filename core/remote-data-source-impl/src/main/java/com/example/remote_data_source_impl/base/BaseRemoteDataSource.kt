package com.example.remote_data_source_impl.base

import com.example.data.base.BaseResponse
import com.example.data.base.RequestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

open class BaseRemoteDataSource : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        RequestResult.Error(throwable)
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T : BaseResponse> safeApiCall(
        dispatcher: CoroutineDispatcher,
        request: suspend () -> T
    ): RequestResult<T> = withContext(dispatcher + coroutineExceptionHandler) {
        return@withContext try {
            val response = request.invoke()
            val condition = response as BaseResponse
            if (response.code == 0)
                RequestResult.Success(condition as T)
            else RequestResult.Warning(response.info, response)
        } catch (throwable: Throwable) {
            RequestResult.Error(throwable)
        }
    }
}
