package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import javax.inject.Inject

class ProductsRespositoryImpl @Inject constructor(
    private val networkDataSource: ProductsNetworkDataSource
) : ProductsRespository {
    override suspend fun getProductList(): Result<List<ProductCluster>> {
        return networkDataSource.getProducts()
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