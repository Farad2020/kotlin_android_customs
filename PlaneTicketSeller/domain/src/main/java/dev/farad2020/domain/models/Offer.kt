package dev.farad2020.domain.models

import com.google.gson.annotations.SerializedName

data class OffersResponse (
    @SerializedName("offers" ) var offers : List<Offers> = listOf()
)

data class Offers (
    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("title" ) var title : String? = null,
    @SerializedName("town"  ) var town  : String? = null,
    @SerializedName("price" ) var price : Price?  = Price()
)