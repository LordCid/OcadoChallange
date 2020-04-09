package com.example.ocadochallenge.repository.rest

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster

interface ProductsNetworkDataSource {
    suspend fun getProducts(): Result<List<ProductCluster>>
    suspend fun getProduct(id: Int): Result<Product>
}