package dev.farad2020.data.model

import java.text.DecimalFormat


data class PriceData (
    val value : Int
){
    fun getFormattedPrice(): String{
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(value.toLong()).replace(',', ' ') + " ₽"
    }

}
