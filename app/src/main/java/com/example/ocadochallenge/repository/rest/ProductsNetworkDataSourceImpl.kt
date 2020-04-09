package com.example.ocadochallenge.repository.rest

import com.example.ocadochallenge.domain.Mapper
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import retrofit2.awaitResponse
import javax.inject.Inject

class ProductsNetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: @JvmSuppressWildcards Mapper<ProductClusterListNetworkModel, List<ProductCluster>>
) : ProductsNetworkDataSource {
    override suspend fun getProducts(): Result<List<ProductCluster>> {
        return runCatching {
            apiService.getProductList().awaitResponse()
        }.fold(
            onSuccess = {
                val clusterList = it.body()?.let {
                        response -> mapper.map(response)
                }.orEmpty()
                Result.success(clusterList)
            },
            onFailure = { Result.failure(it) }
        )
    }

    override suspend fun getProduct(id: Int): Result<Product> {
        val result =apiService.getProduct(id)
        return Result.success(Product(
            id = 12345,
            price = "1.45",
            title = "some title",
            size = "6 units",
            imageUrl = "image"
        ))
    }

}