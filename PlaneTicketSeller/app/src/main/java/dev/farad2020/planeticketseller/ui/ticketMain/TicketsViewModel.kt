package dev.farad2020.planeticketseller.ui.ticketMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.PopularPlaceItem
import dev.farad2020.domain.api.MockApi
import dev.farad2020.domain.models.OffersResponse
import dev.farad2020.domain.usecases.GetOffersUseCase
import dev.farad2020.domain.utils.NetworkResult
import dev.farad2020.domain.utils.handleNetworkResult
import dev.farad2020.planeticketseller.ui.base.DataMapper
import kotlinx.coroutines.launch

//TODO maybe add basevm to check for connectivity
class TicketsViewModel(
    private val getOffersUseCase: GetOffersUseCase,
) : ViewModel() {

    private val _offers = MutableLiveData<List<OfferItem>>()
    val offers: LiveData<List<OfferItem>> = _offers

    private val _popularPlaces = MutableLiveData<List<PopularPlaceItem>>()
    val popularPlaces: LiveData<List<PopularPlaceItem>> = _popularPlaces

    fun loadOffers(){
        viewModelScope.launch {
            handleNetworkResult(getOffersUseCase.execute()) { offers ->
                _offers.value = DataMapper.mapToOffers(offers)
            }
        }
    }

    fun loadPopularPlaces(){
        viewModelScope.launch {
            _popularPlaces.value = popularPlaceItems
        }
    }


    companion object{
        private val popularPlaceItems = listOf(
            PopularPlaceItem(
                image = "",
                title = "Стамбул",
                subtitle = "Популярное направление",
            ),
            PopularPlaceItem(
                image = "",
                title = "Сочи",
                subtitle = "Популярное направление",
            ),
            PopularPlaceItem(
                image = "",
                title = "Пхукет",
                subtitle = "Популярное направление",
            ),
        )
    }
}