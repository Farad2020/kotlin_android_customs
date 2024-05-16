package dev.farad2020.domain.usecases

import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetTicketOffersUseCase  {
    suspend fun execute(): Flow<NetworkResult<TicketOffersResponse>>
}