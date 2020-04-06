package com.example.ocadochallenge

import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.rest.model.NetworkBeerModel

object GlobalConstants {
    const val ANY_FOOD = "some food"
    const val ANY_OTHER_FOOD = "some other food"
}

val someNetworkBeerModel = NetworkBeerModel(
    name = "someBeerName",
    tagline = "tagline",
    description = "description",
    image_url = "image",
    abv = 10.0f
)

val someBeerModel = ProductModel(
    name = "someBeerName",
    tagline = "tagline",
    description = "description",
    image = "image",
    abv = 10.0f
)

val someOtherNetworkBeerModel = NetworkBeerModel(
    name = "otherBeerName",
    tagline = "tagline",
    description = "otherDescription",
    image_url = "otherImage",
    abv = 10.0f
)

val someOtherBeerModel =  ProductModel(
    name = "otherBeerName",
    tagline = "tagline",
    description = "otherDescription",
    image = "otherImage",
    abv = 10.0f
)