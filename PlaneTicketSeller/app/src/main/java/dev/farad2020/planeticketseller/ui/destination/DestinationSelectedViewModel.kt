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
import dev.farad2020.domain.usecases.GetOffersUseCase
import dev.farad2020.domain.usecases.GetTicketOffersUseCase
import dev.farad2020.domain.utils.handleNetworkResult
import dev.farad2020.planeticketseller.ui.base.DataMapper
import kotlinx.coroutines.launch

class DestinationSelectedViewModel(
    private val getTicketOffersUseCase: GetTicketOffersUseCase,
) : ViewModel() {

    private val _ticketOffers = MutableLiveData<List<TicketOfferItem>>()
    val ticketOffers: LiveData<List<TicketOfferItem>> = _ticketOffers

    fun loadTickets(maxItemsNumber: Int){
        viewModelScope.launch {
            handleNetworkResult(getTicketOffersUseCase.execute()) { ticketOffers ->
                _ticketOffers.value = DataMapper.mapTicketOffersResponse(ticketOffers).take(maxItemsNumber + 1)
            }
        }
    }
}