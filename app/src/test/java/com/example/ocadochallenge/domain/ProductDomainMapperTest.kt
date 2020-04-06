package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.rest.model.NetworkBeerModel
import com.example.ocadochallenge.someBeerModel
import com.example.ocadochallenge.someNetworkBeerModel
import com.example.ocadochallenge.someOtherBeerModel
import com.example.ocadochallenge.someOtherNetworkBeerModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductDomainMapperTest {

    private lateinit var sut: ProductDomainMapper

    @Before
    fun setUp() {
        sut = ProductDomainMapper()
    }

    @Test
    fun `Should map some network list model to list domain model`() {
        val expected = expectedBeerModel()

        val actual = sut.mapList(givenNetworkModel())

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network list model to list domain model`() {
        val expected = expectedOtherBeerModel()

        val actual = sut.mapList(givenOtherNetworkModel())

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map network list null values to empty string or 0 values`() {
        val expected = expectedEmptyBeerModel()

        val actual = sut.mapList(givenNullValuesNetworkBeerModel())

        assertEquals(expected, actual)
    }

    private fun givenNetworkModel() = listOf(someNetworkBeerModel)

    private fun givenOtherNetworkModel() = listOf(someOtherNetworkBeerModel)

    private fun givenNullValuesNetworkBeerModel(): List<NetworkBeerModel> {
        return listOf(
            NetworkBeerModel(
                name = null,
                tagline = null,
                description = null,
                image_url = null,
                abv = null
            )
        )
    }

    private fun expectedBeerModel() = listOf(someBeerModel)

    private fun expectedOtherBeerModel() = listOf(someOtherBeerModel)

    private fun expectedEmptyBeerModel(): List<ProductModel> {
        return listOf(
            ProductModel(
                name = "",
                tagline = "",
                description = "",
                image = "",
                abv = 0f
            )
        )
    }
}


