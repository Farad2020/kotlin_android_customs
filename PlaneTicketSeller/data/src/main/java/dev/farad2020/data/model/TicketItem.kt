package dev.farad2020.data.model


data class TicketItem (
    val id: Int,
    val badge: String,
    val price: PriceData,
    val providerName: String,
    val company: String,
    val departure: DepartureData,
    val arrival: ArrivalData,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageData,
    val handLuggage: HandLuggageData,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)

data class DepartureData (
    val town: String,
    val date: String,
    val airport: String
)


data class ArrivalData (
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageData (
    val hasLuggage: Boolean,
    val price: PriceData
)

data class HandLuggageData (
    val hasHandLuggage: Boolean,
    val size: String? // Optional field can remain nullable
)
