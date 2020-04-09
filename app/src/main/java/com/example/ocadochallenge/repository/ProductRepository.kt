package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster

interface ProductRepository {
    suspend fun getProductList(): Result<List<ProductCluster>>
    suspend fun getProduct(id: Int): Result<Product>
}