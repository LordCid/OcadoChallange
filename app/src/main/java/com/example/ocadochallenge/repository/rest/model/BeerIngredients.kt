package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class BeerIngredients (
    @SerializedName("malt")
    val malt: List<BeerMalt>? = null,
    @SerializedName("hops")
    val hops: List<BeerHops>? = null,
    @SerializedName("yeast")
    val yeast: String? = null
)
