package dev.farad2020.planeticketseller.ui.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dev.farad2020.data.model.OfferItem
import dev.farad2020.domain.api.MockApi
import dev.farad2020.domain.models.Offers
import dev.farad2020.domain.models.OffersResponse
import kotlinx.coroutines.launch

class TicketsViewModel : ViewModel() {
    private val gson = Gson()

    private val _offers = MutableLiveData<List<OfferItem>>()
    val offers: LiveData<List<OfferItem>> = _offers

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

}