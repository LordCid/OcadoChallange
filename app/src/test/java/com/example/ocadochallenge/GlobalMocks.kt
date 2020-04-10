package com.example.ocadochallenge

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductClusterNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel


private val someNetworkProduct = ProductNetworkModel(
    id = 12345,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

private val someProduct = Product(
    id = 12345,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

fun getProductById(id: Int) = Product(
    id = id,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
)

fun getNetworkProductById(id: Int) = ProductNetworkModel(
    id = id,
    price = "1.45",
    title = "some title",
    size = "6 units",
    imageUrl = "image"
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
    return ProductClusterListNetworkModel(
        clusters = listOf(getNetworkClusterModel())
    )
}

private fun getNetworkClusterModel(): ProductClusterNetworkModel {
    return ProductClusterNetworkModel(
        tag = "tag",
        items = listOf(someNetworkProduct, someNetworkProduct)
    )
}

fun getOtherNetworkModel(): ProductClusterListNetworkModel {
    val someProductNetworkCluster = getOtherNetworkClusterModel()
    return ProductClusterListNetworkModel(
        clusters = listOf(someProductNetworkCluster, someProductNetworkCluster)
    )
}

private fun getOtherNetworkClusterModel(): ProductClusterNetworkModel {
    return ProductClusterNetworkModel(
        tag = "otherTag",
        items = listOf(someNetworkProduct)
    )
}

