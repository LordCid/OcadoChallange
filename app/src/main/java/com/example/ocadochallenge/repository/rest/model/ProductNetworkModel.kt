package com.example.ocadochallenge.repository.rest.model

import com.google.gson.annotations.SerializedName

data class ProductNetworkModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)