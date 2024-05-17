package dev.farad2020.data.model


data class PriceData (
    val value : Int
){
    fun getFormattedPrice(): String{
        return value.toString()
    }

    //TODO add formatter for price value
    fun formatToPriceStr(price: Int): String {
        val reversed = price.toString().reversed()
        val stringBuilder = StringBuilder()
        for (i in reversed.indices) {
            stringBuilder.append(reversed[i])
            if (i > 2 && (i + 1) % 4 == 0) {
                stringBuilder.append(' ')
            }
        }
        return stringBuilder.reversed().toString()
    }
}
