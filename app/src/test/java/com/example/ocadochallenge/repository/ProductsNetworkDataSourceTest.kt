package com.example.ocadochallenge.repository

import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSourceImpl
import com.example.ocadochallenge.domain.ProductDomainMapper
import com.example.ocadochallenge.getNetworkModel
import com.example.ocadochallenge.getOtherNetworkModel
import com.example.ocadochallenge.getOtherProductClusterList
import com.example.ocadochallenge.getProductClusterList
import com.example.ocadochallenge.repository.rest.ApiService
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
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

    private val mapper = ProductDomainMapper()

    @Before
    fun setUp() {
        sut = ProductsNetworkDataSourceImpl(apiService, mapper)
    }

    @Test
    fun `Given Success request, domain result List is returned`() {
        runBlocking {
            val expectedProductClusterList = getProductClusterList()
            givenNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProducts()
            assertEquals(Result.success(expectedProductClusterList), actual)
        }
    }

    @Test
    fun `Given Success response for OTHER  food, domain reuslt list is returned`() {
        runBlocking {
            val expectedProductClusterList = getOtherProductClusterList()
            givenOtherNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProducts()
            assertEquals(Result.success(expectedProductClusterList), actual)
        }
    }

    @Test
    fun `Should return failure when response is Error`() {
        runBlocking {
            givenNetworkResponseKO()

            val result = sut.getProducts()
            assert(result.isFailure)
        }
    }

    private fun givenNetworkResponseOK() {
        given(apiService.getProducts())
            .willReturn(Calls.response(getNetworkModel()))
    }

    private fun givenOtherNetworkResponseOK() {
        given(apiService.getProducts())
            .willReturn(Calls.response(getOtherNetworkModel()))
    }

    private fun givenNetworkResponseKO() {
        given(apiService.getProducts())
            .willReturn(Calls.failure(Exception()))
    }
}