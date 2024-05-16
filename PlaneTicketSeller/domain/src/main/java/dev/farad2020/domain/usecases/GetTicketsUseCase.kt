package dev.farad2020.domain.usecases

import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetTicketsUseCase  {
    suspend fun execute(): Flow<NetworkResult<TicketsResponse>>
}