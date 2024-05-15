package dev.farad2020.planeticketseller.ui.base

import dev.farad2020.data.model.ArrivalData
import dev.farad2020.data.model.DepartureData
import dev.farad2020.data.model.HandLuggageData
import dev.farad2020.data.model.LuggageData
import dev.farad2020.data.model.PriceData
import dev.farad2020.data.model.TicketItem
import dev.farad2020.domain.models.TicketsResponse

object DataMapper{


//    This is an example mapper, edit in the future, for more dynamic data
    fun mapToTicketData(data: TicketsResponse): List<TicketItem>{
        return data.tickets.map { ticket ->
            TicketItem(
                id = ticket.id!!, // Force unwrap since we have non-null classes
                badge = ticket.badge!!,
                price = PriceData(ticket.price!!.value!!), // Force unwrap non-null values
                providerName = ticket.providerName!!,
                company = ticket.company!!,
                departure = DepartureData(ticket.departure!!.town!!, ticket.departure!!.date!!, ticket.departure!!.airport!!),
                arrival = ArrivalData(ticket.arrival!!.town!!, ticket.arrival!!.date!!, ticket.arrival!!.airport!!),
                hasTransfer = ticket.hasTransfer!!,
                hasVisaTransfer = ticket.hasVisaTransfer!!,
                luggage = LuggageData(ticket.luggage!!.hasLuggage!!, PriceData(ticket.luggage!!.price!!.value!!)),
                handLuggage = HandLuggageData(ticket.handLuggage!!.hasHandLuggage!!, ticket.handLuggage!!.size),
                isReturnable = ticket.isReturnable!!,
                isExchangable = ticket.isExchangable!!
            )
        }
    }
}