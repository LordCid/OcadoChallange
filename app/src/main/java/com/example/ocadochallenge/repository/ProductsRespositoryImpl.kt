package com.example.ocadochallenge.repository

import com.example.brewdogbeers.repository.ProductsRespository
import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSource
import javax.inject.Inject

class ProductsRespositoryImpl @Inject constructor(
    private val networkDataSource: ProductsNetworkDataSource
) : ProductsRespository {
    override suspend fun getBeersForFood(foodName: String): Result<List<ProductModel>> {
        return networkDataSource.getBeersFromFood(foodName)
//        return if(foodName == localDataSource.getPreviouslySearchedFoodName()) {
//           localDataSource.getBeerList()
//
//        } else{
//            networkDataSource.getBeersFromFood(foodName).onSuccess {
//                localDataSource.storeBeerList(foodName, it)
//            }
//        }
    }
}