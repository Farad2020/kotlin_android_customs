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


fun getCurrentDateInRussianFormat(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale("ru"))
    return dateFormat.format(calendar.time)
}

fun formatTime24H(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)
}