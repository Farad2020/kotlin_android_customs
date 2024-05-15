package dev.farad2020.planeticketseller.ui.ticketList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.PopularPlaceItem
import dev.farad2020.data.model.TicketItem
import dev.farad2020.domain.api.MockApi
import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.models.TicketsResponse
import dev.farad2020.planeticketseller.ui.base.DataMapper
import kotlinx.coroutines.launch

class TicketListViewModel : ViewModel() {
    private val gson = Gson()

    private val _tickets = MutableLiveData<List<TicketItem>>()
    val tickets: LiveData<List<TicketItem>> = _tickets

    private fun getMockTickets() = MockApi.getTicketResponseText()

    fun loadTickets(maxItemsNumber: Int){
        viewModelScope.launch {
            val mockData = getMockTickets()
            val response = gson.fromJson(mockData, TicketsResponse::class.java)

//            TODO when backend added, move casting to other layers
//            '+ 1' - means, that last element should be swapped with "showMore" btn
            _tickets.value =  DataMapper.mapToTicketData(response).take(maxItemsNumber + 1)
        }
    }
}