package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class BeerHops(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("amount")
    val amount: QuantityValue? = null,
    @SerializedName("add")
    val add: String? = null,
    @SerializedName("attribute")
    val attribute: String? = null
)


