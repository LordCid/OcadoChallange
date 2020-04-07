package com.example.ocadochallenge.repository.rest.model

import com.google.gson.annotations.SerializedName

data class ProductClusterListNetworkModel(
    @SerializedName("clusters")
    val clusters: List<ProductClusterNetworkModel>
)