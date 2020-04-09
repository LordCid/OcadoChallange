package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel

interface ProductMapper {
    fun mapList(model: ProductClusterListNetworkModel): List<ProductCluster>

    fun mapProduct(model: ProductNetworkModel): Product
}