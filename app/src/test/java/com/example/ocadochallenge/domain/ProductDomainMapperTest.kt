package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductClusterNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import com.example.ocadochallenge.someProduct
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductDomainMapperTest {

    private val someNetworkProduct = ProductNetworkModel(
        id = 12345,
        price = "1.45",
        title = "some title",
        size = "6 units",
        imageUrl = "image"
    )

    private val someProductClusterModel = ProductCluster(
        tag = "someTag",
        products = listOf(someProduct, someProduct)
    )

    private lateinit var sut: ProductDomainMapper

    @Before
    fun setUp() {
        sut = ProductDomainMapper()
    }

    @Test
    fun `Should map some network list model to list domain model`() {
        val expected = expectedProductClusterList()

        val actual = sut.map(givenNetworkModel())

        assertEquals(expected, actual)
    }

//    @Test
//    fun `Should map OTHER network list model to list domain model`() {
//        val expected = expectedOtherBeerModel()
//
//        val actual = sut.mapList(givenOtherNetworkModel())
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `Should map network list null values to empty string or 0 values`() {
//        val expected = expectedEmptyBeerModel()
//
//        val actual = sut.mapList(givenNullValuesNetworkBeerModel())
//
//        assertEquals(expected, actual)
//    }

    private fun givenNetworkModel(): ProductClusterListNetworkModel {
        val someProductNetworkCluster = ProductClusterNetworkModel(
            tag = "someTag",
            items = listOf(someNetworkProduct, someNetworkProduct)
        )
        return ProductClusterListNetworkModel(
            clusters = listOf(someProductNetworkCluster)
        )
    }

    private fun expectedProductClusterList(): List<ProductCluster> {
        return listOf(someProductClusterModel)
    }
}


