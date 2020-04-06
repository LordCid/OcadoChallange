package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class QuantityValue (
    @SerializedName("value")
    val value: Float? = null,
    @SerializedName("unit")
    val unit: String? = null
)
