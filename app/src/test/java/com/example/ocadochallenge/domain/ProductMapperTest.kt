package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.getNetworkModel
import com.example.ocadochallenge.getNetworkProductById
import com.example.ocadochallenge.getOtherNetworkModel
import com.example.ocadochallenge.getOtherProductClusterList
import com.example.ocadochallenge.getProductById
import com.example.ocadochallenge.getProductClusterList
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductMapperTest {

    private lateinit var sut: ProductMapperImp

    @Before
    fun setUp() {
        sut = ProductMapperImp()
    }

    @Test
    fun `Should map some network model to domain model`() {
        val networkModel = getNetworkModel()
        val expected = getProductClusterList()

        val actual = sut.mapList(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network list model to list domain model`() {
        val networkModel = getOtherNetworkModel()
        val expected = getOtherProductClusterList()

        val actual = sut.mapList(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map list network null values to empty string or 0 values`() {
        val networkModel = givenNullValuesNetworkModel()
        val expected = expectedEmptyProductClusterList()

        val actual = sut.mapList(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map network model to domain model`() {
        val someId = 12345
        val networkModel = getNetworkProductById(someId)
        val expected = getProductById(someId)

        val actual = sut.mapProduct(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network model to domain model`() {
        val someId = 6789
        val networkModel = getNetworkProductById(someId)
        val expected = getProductById(someId)

        val actual = sut.mapProduct(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map product network null values to empty string or 0 values`() {
        val networkModel = getNullValuesNetworkModel()
        val expected = getEmptyProductModel()

        val actual = sut.mapProduct(networkModel)

        assertEquals(expected, actual)
    }

    private fun givenNullValuesNetworkModel() = ProductClusterListNetworkModel(
        clusters = null
    )

    private fun expectedEmptyProductClusterList(): List<ProductCluster> {
        return emptyList()
    }

    private fun getNullValuesNetworkModel() = ProductNetworkModel(
        id = null,
        price = null,
        title = null,
        size = null,
        imageUrl = null,
        description = null,
        allergyInformation = null
    )


    private fun getEmptyProductModel() = Product(
        id = 0,
        price = "",
        title = "",
        size = "",
        imageUrl = "",
        description = "",
        allergyInformation = ""
    )
}


