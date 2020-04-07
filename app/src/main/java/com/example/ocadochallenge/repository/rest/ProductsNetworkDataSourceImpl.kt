package com.example.brewdogbeers.repository.rest

import com.example.ocadochallenge.domain.Mapper
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.ApiService
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import javax.inject.Inject

class ProductsNetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: @JvmSuppressWildcards Mapper<ProductClusterListNetworkModel, List<ProductCluster>>
) : ProductsNetworkDataSource {
    override suspend fun getProducts(): Result<List<ProductCluster>> {
        return Result.success(emptyList())

//        return runCatching {
//            apiService.getProducts().awaitResponse()
//        }.fold(
//            onSuccess = {
//                val beerList = it.body()?.let {
//                        response -> mapper.mapList(response)
//                }.orEmpty()
//                Result.success(beerList)
//            },
//            onFailure = { Result.failure(it) }
//        )
    }
}