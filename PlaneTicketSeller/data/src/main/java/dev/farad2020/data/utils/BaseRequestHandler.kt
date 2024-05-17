package dev.farad2020.data.utils

import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleRequest(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()

        val body = response.body()

        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.RequestError(code = response.code(), message = response.message())
        }

    } catch (e: HttpException) {
        NetworkResult.RequestError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e, e.message ?: "no error message")
    }
}
suspend fun <T : Any> handleRequestAsFlow(
    execute: suspend () -> Response<T>
): Flow<NetworkResult<T>> = flow<NetworkResult<T>> {
    emit(NetworkResult.Loading(true))

    val response = execute()

    val body = response.body()

    if (response.isSuccessful && body != null) {
        emit(NetworkResult.Success(body))
    } else {
        emit(NetworkResult.RequestError(code = response.code(), message = response.message()))
    }

}.catch { e ->
    emit(NetworkResult.Exception(e, e.message ?: "Unknown Error"))
}