package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class BeerMalt (
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("amount")
    val amount: QuantityValue? = null
)