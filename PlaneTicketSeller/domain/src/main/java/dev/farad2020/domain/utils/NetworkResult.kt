package dev.farad2020.domain.utils

import kotlinx.coroutines.flow.Flow

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class RequestError<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    data class Exception<T>(val error: Throwable, val errorMessage: String) : NetworkResult<T>()
}

suspend fun <T> handleNetworkResult(flow: Flow<NetworkResult<T>>, onSuccess: (T) -> Unit) {
    flow.collect { result ->
        when (result) {
            is NetworkResult.Loading -> {
                // Handle loading state
            }
            is NetworkResult.Exception -> {
                // Handle exception
            }
            is NetworkResult.RequestError -> {
                // Handle request error
            }
            is NetworkResult.Success -> {
                // Call custom function for success case
                onSuccess(result.data)
            }
        }
    }
}