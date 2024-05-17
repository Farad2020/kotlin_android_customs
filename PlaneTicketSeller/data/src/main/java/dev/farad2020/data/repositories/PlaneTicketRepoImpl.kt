package dev.farad2020.data.repositories

import dev.farad2020.data.service.PlaneTicketService
import dev.farad2020.data.utils.handleRequestAsFlow
import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.domain.repositories.PlaneTicketRepository
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaneTicketRepoImpl(
    private val apiService: PlaneTicketService
):
PlaneTicketRepository{


    override suspend fun getOffers() : Flow<NetworkResult<OffersResponse>>
    = handleRequestAsFlow {
        apiService.fetchOffers()
    }

    override suspend fun getTickets(): Flow<NetworkResult<TicketsResponse>>
    = handleRequestAsFlow {
        apiService.fetchTickets()
    }

    override suspend fun getTicketOffers(): Flow<NetworkResult<TicketOffersResponse>>
    = handleRequestAsFlow {
        apiService.fetchTicketOffers()
    }

}