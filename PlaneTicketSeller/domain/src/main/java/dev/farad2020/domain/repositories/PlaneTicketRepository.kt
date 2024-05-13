package dev.farad2020.domain.repositories

import dev.farad2020.domain.models.Offers
import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PlaneTicketRepository {

    suspend fun getOffers(): Flow<NetworkResult<OffersResponse>>

//    suspend fun getPopularDirections(): Flow<NetworkResult<List<Offers>>>

    suspend fun getTickets(): Flow<NetworkResult<TicketsResponse>>

    suspend fun getTicketOffers(): Flow<NetworkResult<TicketOffersResponse>>
}