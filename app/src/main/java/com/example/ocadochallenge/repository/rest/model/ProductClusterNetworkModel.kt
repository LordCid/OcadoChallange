package com.example.ocadochallenge.repository.rest.model

import com.google.gson.annotations.SerializedName

data class ProductClusterNetworkModel(
    @SerializedName("tag")
    val tag: String? = null,
    @SerializedName("items")
    val items: List<ProductNetworkModel>? = null
)