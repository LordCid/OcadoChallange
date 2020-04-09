package com.example.ocadochallenge.repository

import com.example.ocadochallenge.domain.ProductMapperImp
import com.example.ocadochallenge.getNetworkModel
import com.example.ocadochallenge.getOtherNetworkModel
import com.example.ocadochallenge.getOtherProductClusterList
import com.example.ocadochallenge.getProductClusterList
import com.example.ocadochallenge.getNetworkProductById
import com.example.ocadochallenge.getProductById
import com.example.ocadochallenge.repository.rest.ApiService
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSourceImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.mock.Calls

class ProductsNetworkDataSourceTest {

    private lateinit var sut: ProductsNetworkDataSource

    private val apiService = mock<ApiService>()

    private val listMapper = ProductMapperImp()

    @Before
    fun setUp() {
        sut = ProductsNetworkDataSourceImpl(apiService, listMapper)
    }

    @Test
    fun `Given success result, when getting product List, mapped domain result List is returned`() {
        runBlocking {
            val expectedProductClusterList = getProductClusterList()
            givenNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProductList()
            assertEquals(Result.success(expectedProductClusterList), actual)
        }
    }

    @Test
    fun `Given OTHER success result, when getting product List, mapped domain result List is returned`() {
        runBlocking {
            val expectedProductClusterList = getOtherProductClusterList()
            givenOtherNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProductList()
            assertEquals(Result.success(expectedProductClusterList), actual)
        }
    }

    @Test
    fun `Should return failure when get product list response is Error`() {
        runBlocking {
            givenNetworkGetProductListResponseKO()

            val result = sut.getProducts()
            assert(result.isFailure)
        }
    }

    @Test
    fun `Given success result, when getting product by id, mapped domain result List is returned`() {
        runBlocking {
            val someId = 12345
            val expected = getProductById(someId)
            givenNetworkGetProductByIdResponseOK(someId)

            val actual = sut.getProduct(someId)

            verify(apiService).getProduct(someId)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given OTHER success result, when getting product by id, mapped domain result List is returned`() {
        runBlocking {
            val someId = 4567
            val expected = getProductById(someId)
            givenNetworkGetProductByIdResponseOK(someId)

            val actual = sut.getProduct(someId)

            verify(apiService).getProduct(someId)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Should return failure when get product by Id response is Error`() {
        runBlocking {
            val someId = 12345
            givenNetworkGetProductByIdResponseKO(someId)

            val result = sut.getProduct(someId)

            assert(result.isFailure)
        }
    }

    private fun givenNetworkResponseOK() {
        given(apiService.getProductList())
            .willReturn(Calls.response(getNetworkModel()))
    }

    private fun givenOtherNetworkResponseOK() {
        given(apiService.getProductList())
            .willReturn(Calls.response(getOtherNetworkModel()))
    }

    private fun givenNetworkGetProductListResponseKO() {
        given(apiService.getProductList())
            .willReturn(Calls.failure(mock<Exception>()))
    }

    private fun givenNetworkGetProductByIdResponseOK(someId: Int) {
        given(apiService.getProduct(someId)).
        willReturn(Calls.response(getNetworkProductById(someId)))
    }

    private fun givenNetworkGetProductByIdResponseKO(someId: Int) {
        given(apiService.getProduct(someId))
            .willReturn(Calls.failure(mock<Exception>()))
    }
}