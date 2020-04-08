package com.example.ocadochallenge

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductClusterNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel

object GlobalConstants {
    const val ANY_FOOD = "some food"
    const val ANY_OTHER_FOOD = "some other food"
}

val someNetworkProduct = ProductNetworkModel(
    id = 12345,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

val someProduct = Product(
    id = 12345,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

val someOtherProduct =  Product(
    id = 67891,
    price = "4.45",
    title = "some other title",
    size = "5 units",
    imageUrl = "otherImage"
)

fun getProductClusterList(): List<ProductCluster> {
    val someProductClusterModel = ProductCluster(
        tag = "tag",
        products = listOf(someProduct, someProduct)
    )
    return listOf(someProductClusterModel)
}

fun getOtherProductClusterList(): List<ProductCluster> {
    val someProductClusterModel = ProductCluster(
        tag = "otherTag",
        products = listOf(someProduct)
    )
    return listOf(someProductClusterModel, someProductClusterModel)
}

fun getNetworkModel(): ProductClusterListNetworkModel {
    val someProductNetworkCluster = ProductClusterNetworkModel(
        tag = "tag",
        items = listOf(someNetworkProduct, someNetworkProduct)
    )
    return ProductClusterListNetworkModel(
        clusters = listOf(someProductNetworkCluster)
    )
}

fun getOtherNetworkModel(): ProductClusterListNetworkModel {
    val someProductNetworkCluster = ProductClusterNetworkModel(
        tag = "otherTag",
        items = listOf(someNetworkProduct)
    )
    return ProductClusterListNetworkModel(
        clusters = listOf(someProductNetworkCluster, someProductNetworkCluster)
    )
}


