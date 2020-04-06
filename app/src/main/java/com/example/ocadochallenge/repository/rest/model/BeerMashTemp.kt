package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class BeerMashTemp (
    @SerializedName("mash_temp")
    val mash_temp: QuantityValue? = null,
    @SerializedName("duration")
    val duration: Int? = null
)


