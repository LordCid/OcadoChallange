package com.example.ocadochallenge.repository.rest

import com.example.ocadochallenge.domain.Mapper
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import retrofit2.awaitResponse
import javax.inject.Inject

class ProductsNetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val listMapper: @JvmSuppressWildcards Mapper<ProductClusterListNetworkModel, List<ProductCluster>>,
    val productMapper: @JvmSuppressWildcards  Mapper<ProductNetworkModel, Product>
) : ProductsNetworkDataSource {
    override suspend fun getProducts(): Result<List<ProductCluster>> {
        return runCatching {
            apiService.getProductList().awaitResponse()
        }.fold(
            onSuccess = {
                val clusterList = it.body()?.let {
                        response -> listMapper.map(response)
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
                if(it.body() != null){
                    val product = productMapper.map(it.body() as ProductNetworkModel)
                    Result.success(product)
                } else {
                    Result.failure(Exception("Failure when retreving product"))
                }

            },
            onFailure = { Result.failure(it) }
        )
    }

}