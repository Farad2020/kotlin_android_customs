package dev.farad2020.domain.models

import com.google.gson.annotations.SerializedName

data class TicketOffersResponse(
    @SerializedName("tickets_offers" ) var ticketsOffers : ArrayList<TicketsOffers> = arrayListOf()
)


data class TicketsOffers (
    @SerializedName("id"         ) var id        : Int?              = null,
    @SerializedName("title"      ) var title     : String?           = null,
    @SerializedName("time_range" ) var timeRange : ArrayList<String> = arrayListOf(),
    @SerializedName("price"      ) var price     : Price?            = Price()
)