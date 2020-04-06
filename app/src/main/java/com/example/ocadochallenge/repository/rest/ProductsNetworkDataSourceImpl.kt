package com.example.brewdogbeers.repository.rest

import com.example.ocadochallenge.domain.Mapper
import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.rest.model.NetworkBeerModel
import retrofit2.awaitResponse
import javax.inject.Inject

class ProductsNetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: @JvmSuppressWildcards Mapper<NetworkBeerModel, ProductModel>
) : ProductsNetworkDataSource {
    override suspend fun getBeersFromFood(foodName: String): Result<List<ProductModel>> {

        return runCatching {
            apiService.getBeersForFood(foodName).awaitResponse()
        }.fold(
            onSuccess = {
                val beerList = it.body()?.let {
                        response -> mapper.mapList(response)
                }.orEmpty()
                Result.success(beerList)
            },
            onFailure = { Result.failure(it) }
        )
    }
}