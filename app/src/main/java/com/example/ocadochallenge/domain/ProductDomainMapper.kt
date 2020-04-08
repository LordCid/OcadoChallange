package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel

class ProductDomainMapper : Mapper<ProductClusterListNetworkModel, List<ProductCluster>> {
    override fun map(model: ProductClusterListNetworkModel): List<ProductCluster> {
        val clusterList = model.clusters
        return clusterList?.map {
            ProductCluster(
                tag = it.tag ?:"",
                products = mapToProduct(it.items.orEmpty())
            )
        }.orEmpty()
    }

    private fun mapToProduct(networkProductList: List<ProductNetworkModel>): List<Product> {
        return networkProductList.map { productNetworkModel ->
            Product(
                id = productNetworkModel.id,
                price = productNetworkModel.price,
                title = productNetworkModel.title,
                size = productNetworkModel.size,
                imageUrl = productNetworkModel.imageUrl
            )
        }
    }
}