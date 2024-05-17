package dev.farad2020.data.model


data class OfferItem(
    val id: Int,
    val title: String,
    val city: String,
    val price: Int,
    ){

    fun getFormattedPrice(): String{
        return price.toString()
    }
}

