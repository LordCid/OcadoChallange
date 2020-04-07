package com.example.ocadochallenge

import com.example.ocadochallenge.domain.model.Product

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

val someProduct = Product(
    id = 12345,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

val someOtherNetworkBeerModel = NetworkBeerModel(
    name = "otherBeerName",
    tagline = "tagline",
    description = "otherDescription",
    image_url = "otherImage",
    abv = 10.0f
)

val someOtherProduct =  Product(
    id = 67891,
    price = "4.45",
    title = "some other title",
    size = "5 units",
    imageUrl = "otherImage"
)

