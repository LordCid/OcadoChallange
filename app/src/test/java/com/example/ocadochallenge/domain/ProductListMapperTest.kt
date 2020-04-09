package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.getNetworkModel
import com.example.ocadochallenge.getOtherNetworkModel
import com.example.ocadochallenge.getOtherProductClusterList
import com.example.ocadochallenge.getProductClusterList
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductListMapperTest {

    private lateinit var sut: ProductListMapper

    @Before
    fun setUp() {
        sut = ProductListMapper()
    }

    @Test
    fun `Should map some network model to domain model`() {
        val networkModel = getNetworkModel()
        val expected = getProductClusterList()

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network list model to list domain model`() {
        val networkModel = getOtherNetworkModel()
        val expected = getOtherProductClusterList()

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map network null values to empty string or 0 values`() {
        val networkModel = givenNullValuesNetworkModel()
        val expected = expectedEmptyProductClusterList()

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
    }

    private fun givenNullValuesNetworkModel() = ProductClusterListNetworkModel(
        clusters = null
    )

    private fun expectedEmptyProductClusterList(): List<ProductCluster> {
        return emptyList()
    }
}


