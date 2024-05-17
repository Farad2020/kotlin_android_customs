package dev.farad2020.planeticketseller.ui.navModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TicketListNavPayload (
    val arrivalCity: String,
    val departureCity: String,
    val flightDate: String,
    val flightInfo: String,
) : Parcelable