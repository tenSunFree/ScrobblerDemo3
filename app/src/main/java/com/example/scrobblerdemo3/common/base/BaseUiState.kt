package com.example.scrobblerdemo3.common.base

import android.util.Log

sealed class BaseUiState<out T> {
    data class Success<out T>(
        val data: T?,
        val loading: Boolean
    ) : BaseUiState<T>()

    data class Error<out T>(
        val exception: Throwable? = null,
        val errorMessage: String? = null,
        val previousData: T?
    ) : BaseUiState<T>()

    val isInitialLoading: Boolean
        get() = this is Success && this.loading && this.data == null

    val isRefreshLoading: Boolean
        get() {
            Log.d(
                "more",
                "RefreshableUiState, isRefreshLoading, " +
                        "this is Success: ${this is Success}, " +
                        "this.loading: ${this is Success && this.loading}, " +
                        "this.data != null: ${this is Success && this.data != null}"
            )
            return this is Success && this.loading && this.data != null
        }

    val isError: Boolean
        get() = this is Error

    val currentData: T?
        get() = when (this) {
            is Success -> this.data
            is Error -> this.previousData
        }
}