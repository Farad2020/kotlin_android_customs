package dev.farad2020.domain.models

import com.google.gson.annotations.SerializedName

data class TicketsResponse (
    @SerializedName("tickets" ) var tickets : ArrayList<Tickets> = arrayListOf()
)

data class Tickets (
    @SerializedName("id"                ) var id              : Int?         = null,
    @SerializedName("badge"             ) var badge           : String?      = null,
    @SerializedName("price"             ) var price           : Price?       = null,
    @SerializedName("provider_name"     ) var providerName    : String?      = null,
    @SerializedName("company"           ) var company         : String?      = null,
    @SerializedName("departure"         ) var departure       : Departure?   = null,
    @SerializedName("arrival"           ) var arrival         : Arrival?     = null,
    @SerializedName("has_transfer"      ) var hasTransfer     : Boolean?     = null,
    @SerializedName("has_visa_transfer" ) var hasVisaTransfer : Boolean?     = null,
    @SerializedName("luggage"           ) var luggage         : Luggage?     = null,
    @SerializedName("hand_luggage"      ) var handLuggage     : HandLuggage? = null,
    @SerializedName("is_returnable"     ) var isReturnable    : Boolean?     = null,
    @SerializedName("is_exchangable"    ) var isExchangable   : Boolean?     = null
)

data class Departure (
    @SerializedName("town"    ) var town    : String? = null,
    @SerializedName("date"    ) var date    : String? = null,
    @SerializedName("airport" ) var airport : String? = null
)


data class Arrival (
    @SerializedName("town"    ) var town    : String? = null,
    @SerializedName("date"    ) var date    : String? = null,
    @SerializedName("airport" ) var airport : String? = null
)

data class Luggage (
    @SerializedName("has_luggage" ) var hasLuggage : Boolean,
    @SerializedName("price"       ) var price      : Price?   = Price()
)

data class HandLuggage (
    @SerializedName("has_hand_luggage" ) var hasHandLuggage : Boolean,
    @SerializedName("size"             ) var size           : String?  = null
)

