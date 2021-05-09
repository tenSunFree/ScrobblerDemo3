package com.example.scrobblerdemo3.common.base

/**
 * A generic class that holds a value or an exception
 */
sealed class BaseResult<out R> {
    object Loading : BaseResult<Nothing>()
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val exception: Exception) : BaseResult<Nothing>()
}