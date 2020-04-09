package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.getProductById
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private val networkDataSource = mock<ProductsNetworkDataSource>()

    private lateinit var sut: ProductRepository

    @Before
    fun setUp() {
        sut = ProductRepositoryImpl(networkDataSource)
    }

    @Test
    fun `When get products Should get result from network data source`() {
        runBlocking {
            val expected = Result.success(emptyList<ProductCluster>())
            given(networkDataSource.getProducts()).willReturn(expected)

            val actual = sut.getProductList()

            verify(networkDataSource).getProducts()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `When get product by id, Should get result from network data source`() {
        runBlocking {
            val someId = 12345
            val expected = Result.success(getProductById(someId))
            given(networkDataSource.getProduct(someId)).willReturn(expected)

            val actual = sut.getProduct(someId)

            verify(networkDataSource).getProduct(someId)
            assertEquals(expected, actual)
        }

    }
}