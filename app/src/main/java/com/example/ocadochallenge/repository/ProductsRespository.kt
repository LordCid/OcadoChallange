package com.example.brewdogbeers.repository

import com.example.ocadochallenge.domain.model.ProductModel

interface ProductsRespository {
    suspend fun getBeersForFood(foodName: String): Result<List<ProductModel>>
}