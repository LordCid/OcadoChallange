package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.ProductCluster

interface ProductsRespository {
    suspend fun getProductList(): Result<List<ProductCluster>>
}