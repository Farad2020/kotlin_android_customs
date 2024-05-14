package dev.farad2020.planeticketseller.ui.destination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.PopularPlaceItem
import dev.farad2020.domain.api.MockApi
import dev.farad2020.domain.models.OffersResponse
import kotlinx.coroutines.launch

class DestinationSelectedViewModel : ViewModel() {
    private val gson = Gson()

    private val _offers = MutableLiveData<List<OfferItem>>()
    val offers: LiveData<List<OfferItem>> = _offers

    private val _popularPlaces = MutableLiveData<List<PopularPlaceItem>>()
    val popularPlaces: LiveData<List<PopularPlaceItem>> = _popularPlaces

    private fun getMockOffers() = MockApi.getOffersResponseText()

    fun loadOffers(){
        viewModelScope.launch {
            val mockData = getMockOffers()
            val response = gson.fromJson(mockData, OffersResponse::class.java)

            _offers.value = response.offers.map { offerData ->
                OfferItem(
                    "imageView",
                    offerData.title ?: "",
                    offerData.town ?: "Town",
                    offerData.price?.value ?: 0,
                )
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