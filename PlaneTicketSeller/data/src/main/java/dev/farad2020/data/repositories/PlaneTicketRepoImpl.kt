package dev.farad2020.data.repositories

import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.domain.repositories.PlaneTicketRepository
import dev.farad2020.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class PlaneTicketRepoImpl:
PlaneTicketRepository{


    override suspend fun getOffers() : Flow<NetworkResult<OffersResponse>> {
        TODO("Not yet implemented")
    }

//    = flow{
//        emit(NetworkResult.Loading(true))
////        val response = apiService.getOffers()
////        emit(NetworkResult.Success(response))
//    }.catch { e ->
//        emit(NetworkResult.Exception(e, e.message ?: "Unknown Error"))
//    }

    override suspend fun getTickets(): Flow<NetworkResult<TicketsResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTicketOffers(): Flow<NetworkResult<TicketOffersResponse>> {
        TODO("Not yet implemented")
    }


}