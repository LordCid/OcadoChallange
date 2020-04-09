package com.example.ocadochallenge.domain.model

data class Product(
    val id: Int,
    val price: String,
    val title: String,
    val size: String,
    val imageUrl: String,
    val description: String = "",
    val allergyInformation: String = ""
)