package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel

class ProductMapperImp : ProductMapper {
    override fun mapList(model: ProductClusterListNetworkModel): List<ProductCluster> {
        val clusterList = model.clusters
        return clusterList?.map {
            ProductCluster(
                tag = it.tag ?: "",
                products = it.items?.map { item -> mapProduct(item) }.orEmpty()
            )
        }.orEmpty()
    }

    override fun mapProduct(model: ProductNetworkModel): Product {
        return Product(
            id = model.id ?: 0,
            price = model.price ?: "",
            title = model.title ?: "",
            size = model.size ?: "",
            imageUrl = model.imageUrl ?: "",
            description = model.description ?: "",
            allergyInformation = model.allergyInformation ?: ""
        )
    }
}