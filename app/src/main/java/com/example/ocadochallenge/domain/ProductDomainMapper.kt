package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel

class ProductDomainMapper : Mapper<ProductClusterListNetworkModel, List<ProductCluster>> {
    override fun map(model: ProductClusterListNetworkModel): List<ProductCluster> {
        return emptyList()
    }
}