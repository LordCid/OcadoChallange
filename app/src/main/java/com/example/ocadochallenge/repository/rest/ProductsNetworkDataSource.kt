package com.example.brewdogbeers.repository.rest

import com.example.ocadochallenge.domain.model.ProductModel

interface ProductsNetworkDataSource {
    suspend fun getBeersFromFood(foodName: String): Result<List<ProductModel>>
}