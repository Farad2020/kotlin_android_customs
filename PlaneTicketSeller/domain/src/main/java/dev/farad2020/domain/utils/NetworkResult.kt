package dev.farad2020.domain.utils

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class RequestError<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    data class Exception<T>(val error: Throwable, val errorMessage: String) : NetworkResult<T>()
}