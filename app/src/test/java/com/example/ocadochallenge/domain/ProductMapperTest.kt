package com.example.ocadochallenge.domain

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.getNetworkProductById
import com.example.ocadochallenge.getProductById
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ProductMapperTest {

    private lateinit var sut: Mapper<ProductNetworkModel, Product>

    @Before
    fun setUp() {
        sut = ProductMapper()
    }

    @Test
    fun `Should map network model to domain model`() {
        val someId = 12345
        val networkModel = getNetworkProductById(someId)
        val expected = getProductById(someId)

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network model to domain model`() {
        val someId = 6789
        val networkModel = getNetworkProductById(someId)
        val expected = getProductById(someId)

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map network null values to empty string or 0 values`() {
        val networkModel = getNullValuesNetworkModel()
        val expected = getEmptyProductModel()

        val actual = sut.map(networkModel)

        assertEquals(expected, actual)
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

