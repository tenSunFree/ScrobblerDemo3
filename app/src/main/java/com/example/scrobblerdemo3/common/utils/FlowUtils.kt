package com.example.scrobblerdemo3.common.utils

import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.fresh
import com.example.scrobblerdemo3.common.base.BaseResult
import com.example.scrobblerdemo3.common.base.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.net.UnknownHostException

@Suppress("TooGenericExceptionCaught")
suspend fun <Key : Any, StateType : Any, Output : StateType> MutableStateFlow<BaseUiState<StateType>>.freshFrom(
    store: Store<Key, Output>,
    key: Key
) {
    return refreshStateFlowFromStore(this, store, key)
}

suspend fun <Key : Any, StateType : Any, Output : StateType> MutableStateFlow<BaseUiState<StateType>>.streamFrom(
    store: Store<Key, Output>,
    key: Key
) = store.stream(StoreRequest.cached(key, true)).collectLatest { update(it) }

@Suppress("TooGenericExceptionCaught")
suspend inline fun <Key : Any, StateType : Any, Output : StateType> refreshStateFlowFromStore(
    flow: MutableStateFlow<BaseUiState<StateType>>?,
    store: Store<Key, Output>,
    key: Key
) {
    flow?.update(BaseResult.Loading)
    try {
        store.fresh(key)
    } catch (e: Exception) {
        flow?.update(BaseResult.Error(e))
    }
}

fun <T> MutableStateFlow<T>.updateValue(newValue: T): Boolean {
    if (value != newValue) {
        value = newValue
        return true
    }
    return false
}

fun <T> MutableStateFlow<BaseUiState<T>>.update(baseResult: BaseResult<T>) {
    value = when (baseResult) {
        is BaseResult.Success -> BaseUiState.Success(
            data = baseResult.data, loading = false
        )
        is BaseResult.Error -> {
            BaseUiState.Error(
                exception = baseResult.exception,
                previousData = this.value.currentData,
                errorMessage = extractErrorMessageFromException(baseResult.exception)
            )
        }
        is BaseResult.Loading -> BaseUiState.Success(
            data = this.value.currentData, loading = true
        )
    }
}

fun <T> MutableStateFlow<BaseUiState<T>>.update(result: StoreResponse<T>) {
    value = when (result) {
        is StoreResponse.Data -> BaseUiState.Success(
            data = result.value, loading = false
        )
        is StoreResponse.Error.Exception -> BaseUiState.Error(
            exception = result.error,
            previousData = this.value.currentData,
            errorMessage = extractErrorMessageFromException(result.error)
        ).also {
            // Timber.e(it.exception)
        }
        is StoreResponse.Error.Message -> BaseUiState.Error(
            errorMessage = result.message, previousData = this.value.currentData
        )
        is StoreResponse.Loading -> BaseUiState.Success(
            data = this.value.currentData, loading = true
        )
        is StoreResponse.NoNewData -> BaseUiState.Success(
            data = this.value.currentData, loading = false
        )
    }
}

private fun extractErrorMessageFromException(exception: Throwable): String? {
    return when (exception) {
        is UnknownHostException -> {
            "Network unavailable"
        }
        else -> null
    }
}