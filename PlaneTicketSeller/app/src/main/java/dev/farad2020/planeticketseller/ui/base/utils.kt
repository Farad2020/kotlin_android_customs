package dev.farad2020.planeticketseller.ui.base

import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun View.visible(isVisible: Boolean, visibilityType: Int = View.INVISIBLE) {
    visibility = if (isVisible) View.VISIBLE else visibilityType
}

fun View.gone(isGone: Boolean, visibilityType: Int = View.VISIBLE) {
    visibility = if (isGone) View.GONE else visibilityType
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


fun getCurrentDateInRussianFormat(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale("ru"))
    return dateFormat.format(calendar.time)
}