package dev.farad2020.data.service

import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlaneTicketService {

    @GET("/214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun fetchOffers(): Response<OffersResponse>

    @GET("/670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun fetchTickets(): Response<TicketsResponse>

    @GET("/7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun fetchTicketOffers(): Response<TicketOffersResponse>
}
