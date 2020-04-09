package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val networkDataSource: ProductsNetworkDataSource
) : ProductRepository {
    override suspend fun getProductList(): Result<List<ProductCluster>> {
        return networkDataSource.getProducts()

    }

    override suspend fun getProduct(id: Int): Result<Product> {
        return networkDataSource.getProduct(id)
    }
}