package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel

class ProductMapper: Mapper<ProductNetworkModel, Product> {
    override fun map(model: ProductNetworkModel): Product {
        return Product(
            id = model.id ?:0,
            price = model.price ?:"",
            title = model.title ?:"",
            size = model.size ?:"",
            imageUrl = model.imageUrl ?:"",
            description = model.description ?:"",
            allergyInformation = model.allergyInformation ?:""
        )
    }
}