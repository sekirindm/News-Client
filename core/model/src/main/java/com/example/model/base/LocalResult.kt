package com.example.model.base

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class LocalResult<out R> {

    data class Success<out T>(val data: T) : LocalResult<T>()
    data class Error(val exception: Exception) : LocalResult<Nothing>()

    override fun toString() = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[exception=$exception]"
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val LocalResult<*>.succeeded
    get() = this is LocalResult.Success && data != null