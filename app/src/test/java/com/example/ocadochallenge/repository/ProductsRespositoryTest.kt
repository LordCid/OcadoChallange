package com.example.ocadochallenge.repository

import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.someProduct
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ProductsRespositoryTest {

    private val networkDataSource = mock<ProductsNetworkDataSource>()

    private lateinit var sut: ProductsRespository

    private val failureException = Throwable()

    @Before
    fun setUp() {
        sut = ProductsRespositoryImpl(networkDataSource)
    }

    @Test
    fun `Should get product list`() {
        runBlocking {
            val expected = listOf(someProduct)
            givenProductListFromNetworkDatasource(expected)

            val actual = sut.getProductList()

            verify(networkDataSource).getProducts(ANY_FOOD)
            assertEquals(Result.success(expected), actual)
        }
    }


    private suspend fun givenProductListFromNetworkDatasource(productList: List<Product>) {
        given(networkDataSource.getProducts(anyString())).willReturn(Result.success(productList))
    }

    private suspend fun givenFailureFromNetworkDatasource() {
        given(networkDataSource.getProducts(anyString())).willReturn(Result.failure(failureException))
    }



}