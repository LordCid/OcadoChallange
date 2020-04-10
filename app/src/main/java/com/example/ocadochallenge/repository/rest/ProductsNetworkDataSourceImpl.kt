package com.example.ocadochallenge.repository.rest

import com.example.ocadochallenge.domain.ProductMapper
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import retrofit2.awaitResponse
import javax.inject.Inject

class ProductsNetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val listMapper: @JvmSuppressWildcards ProductMapper
) : ProductsNetworkDataSource {
    override suspend fun getProducts(): Result<List<ProductCluster>> {
        return runCatching {
            apiService.getProductList().awaitResponse()
        }.fold(
            onSuccess = {
                val clusterList = it.body()?.let { response ->
                    listMapper.mapList(response)
                }.orEmpty()
                Result.success(clusterList)
            },
            onFailure = { Result.failure(it) }
        )
    }

    override suspend fun getProduct(id: Int): Result<Product> {
        return runCatching {
            apiService.getProduct(id).awaitResponse()
        }.fold(
            onSuccess = {
                if (it.body() != null) {
                    val product = listMapper.mapProduct(it.body() as ProductNetworkModel)
                    Result.success(product)
                } else {
                    Result.failure(Exception("Failure when retrieving product"))
                }

            },
            onFailure = { Result.failure(it) }
        )
    }
}