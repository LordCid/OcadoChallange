package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class BeerMethod (
    @SerializedName("mash_temp")
    val mash_temp: List<BeerMashTemp>? = null,
    @SerializedName("fermentation")
    val fermentation: QuantityValue? = null,
    @SerializedName("twist")
    val twist: String? = null
)
