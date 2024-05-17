package dev.farad2020.planeticketseller.ui.navModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DestinationFragmentNavPayload (
    val arrivalCity: String,
    val departureCity: String,
) : Parcelable