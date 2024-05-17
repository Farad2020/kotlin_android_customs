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
import dev.farad2020.domain.usecases.GetTicketOffersUseCase
import dev.farad2020.domain.usecases.GetTicketsUseCase
import dev.farad2020.domain.utils.handleNetworkResult
import dev.farad2020.planeticketseller.ui.base.DataMapper
import kotlinx.coroutines.launch

class TicketListViewModel(
    private val getTicketsUseCase: GetTicketsUseCase,
) : ViewModel() {
    private val _tickets = MutableLiveData<List<TicketItem>>()
    val tickets: LiveData<List<TicketItem>> = _tickets

    fun loadTickets(){
        viewModelScope.launch {
            handleNetworkResult(getTicketsUseCase.execute()) { tickets ->
                _tickets.value = DataMapper.mapToTicketData(tickets)
            }
        }
    }
}