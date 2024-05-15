package dev.farad2020.planeticketseller.ui.destination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dev.farad2020.data.model.TicketItem
import dev.farad2020.data.model.TicketOfferItem
import dev.farad2020.domain.api.MockApi
import dev.farad2020.domain.models.TicketOffersResponse
import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.planeticketseller.ui.base.DataMapper
import kotlinx.coroutines.launch

class DestinationSelectedViewModel : ViewModel() {
    private val gson = Gson()

    private val _ticketOffers = MutableLiveData<List<TicketOfferItem>>()
    val ticketOffers: LiveData<List<TicketOfferItem>> = _ticketOffers

    private fun getMockTicketOffers() = MockApi.getTicketOffersResponseText()

    fun loadTickets(maxItemsNumber: Int){
        viewModelScope.launch {
            val mockData = getMockTicketOffers()
            val response = gson.fromJson(mockData, TicketOffersResponse::class.java)

//            TODO when backend added, move casting to other layers
//            '+ 1' - means, that last element should be swapped with "showMore" btn
            _ticketOffers.value =  DataMapper.mapTicketOffersResponse(response).take(maxItemsNumber + 1)
        }
    }
}