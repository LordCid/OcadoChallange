package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.getProductClusterList
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsRespositoryTest {

    private val networkDataSource = mock<ProductsNetworkDataSource>()

    private lateinit var sut: ProductsRespository

    @Before
    fun setUp() {
        sut = ProductsRespositoryImpl(networkDataSource)
    }

    @Test
    fun `Should get result from network datasource`() {
        runBlocking {
            val expected = Result.success(emptyList<ProductCluster>())
            given(networkDataSource.getProducts()).willReturn(expected)

            val actual = sut.getProductList()

            verify(networkDataSource).getProducts()
            assertEquals(expected, actual)
        }
    }

}