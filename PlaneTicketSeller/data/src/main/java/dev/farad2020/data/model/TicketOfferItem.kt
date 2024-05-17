package dev.farad2020.data.model

data class TicketOfferItem (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceData
)