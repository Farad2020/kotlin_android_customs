package dev.farad2020.planeticketseller.ui.base

import dev.farad2020.data.model.ArrivalData
import dev.farad2020.data.model.DepartureData
import dev.farad2020.data.model.HandLuggageData
import dev.farad2020.data.model.LuggageData
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.PriceData
import dev.farad2020.data.model.TicketItem
import dev.farad2020.data.model.TicketOfferItem
import dev.farad2020.domain.models.HandLuggage
import dev.farad2020.domain.models.Luggage
import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.Price
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse

//TODO refactor mapper

object DataMapper{

    fun mapToOffers(offersResponse: OffersResponse): List<OfferItem>{
        return offersResponse.offers.map { offerData ->
                OfferItem(
                    offerData.id ?: 1,
                    offerData.title ?: "",
                    offerData.town ?: "Town",
                    offerData.price?.value ?: 0,
                )
            }
    }


//    This is an example mapper, edit in the future, for more dynamic data
    fun mapToTicketData(data: TicketsResponse): List<TicketItem>{
        return data.tickets.map { ticket ->

            TicketItem(
                id = ticket.id!!, // Force unwrap since we have non-null classes
                badge = ticket.badge,
                price = mapPriceToData(ticket.price!!), // Force unwrap non-null values
                providerName = ticket.providerName!!,
                company = ticket.company!!,
                departure = DepartureData(ticket.departure!!.town!!, ticket.departure!!.date!!, ticket.departure!!.airport!!),
                arrival = ArrivalData(ticket.arrival!!.town!!, ticket.arrival!!.date!!, ticket.arrival!!.airport!!),
                hasTransfer = ticket.hasTransfer!!,
                hasVisaTransfer = ticket.hasVisaTransfer!!,
                luggage = prepareLuggageData(ticket.luggage),
                handLuggage = prepareHandLuggageData(ticket.handLuggage),
                isReturnable = ticket.isReturnable!!,
                isExchangable = ticket.isExchangable!!
            )
        }
    }

    private fun prepareLuggageData(data: Luggage?): LuggageData?{
        if(data == null)
            return null

        val priceData =  data.price?.let { mapPriceToData(it)}

        return LuggageData(data.hasLuggage, priceData)
    }

    private fun prepareHandLuggageData(data: HandLuggage?): HandLuggageData?{
        if(data == null)
            return null

        val size = data.size

        return HandLuggageData(
            data.hasHandLuggage,
            size
        )
    }


//    This is an example mapper, edit in the future, for more dynamic data
    fun mapTicketOffersResponse(response: TicketOffersResponse): List<TicketOfferItem> {
        return response.ticketsOffers.map { ticketOffer ->
            TicketOfferItem(
                id = ticketOffer.id!!,
                title = ticketOffer.title!!,
                timeRange = ticketOffer.timeRange,
                price = mapPriceToData(ticketOffer.price!!)
            )
        }
    }

    fun mapPriceToData(price: Price): PriceData = PriceData(price.value!!)
}