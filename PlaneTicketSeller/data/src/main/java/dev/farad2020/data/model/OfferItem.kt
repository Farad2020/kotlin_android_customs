package dev.farad2020.data.model

import java.text.DecimalFormat


data class OfferItem(
    val id: Int,
    val title: String,
    val city: String,
    val price: Int,
    ){

    fun getFormattedPrice(): String{
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price.toLong()).replace(',', ' ') + " ₽"
    }
}

