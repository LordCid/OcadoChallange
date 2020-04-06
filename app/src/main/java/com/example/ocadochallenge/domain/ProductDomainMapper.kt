package com.example.ocadochallenge.domain

import com.example.brewdogbeers.repository.rest.model.NetworkBeerModel
import com.example.ocadochallenge.domain.model.ProductModel

class ProductDomainMapper : Mapper<NetworkBeerModel, ProductModel> {
    override fun map(model: NetworkBeerModel): ProductModel {
        return ProductModel(
            name = model.name ?: "",
            tagline = model.tagline ?: "",
            description = model.description ?: "",
            image = model.image_url ?: "",
            abv = model.abv ?: 0f
        )
    }
}