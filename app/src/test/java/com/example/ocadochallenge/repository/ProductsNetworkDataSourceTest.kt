package com.example.ocadochallenge.repository

import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.GlobalConstants.ANY_OTHER_FOOD
import com.example.ocadochallenge.domain.ProductDomainMapper
import com.example.ocadochallenge.repository.rest.ApiService
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSourceImpl
import com.example.ocadochallenge.someProduct
import com.example.ocadochallenge.someNetworkBeerModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.mock.Calls

class ProductsNetworkDataSourceTest {

    private val someBeerDataResults = listOf(someNetworkBeerModel, someNetworkBeerModel)
    private val someOtherBeerDataResults = listOf(someNetworkBeerModel)

    private lateinit var sut: ProductsNetworkDataSource

    private val apiService = mock<ApiService>()

    private val mapper = ProductDomainMapper()

    @Before
    fun setUp() {
        sut = ProductsNetworkDataSourceImpl(apiService, mapper)
    }

    @Test
    fun `Given food name, should request api for beers that match this food`() {
        runBlocking {
            givenNetworkResponseOK()

            sut.getProducts()

            verify(apiService).getProducts()
        }
    }

    @Test
    fun `Given Success request, domain result List is returned`() {
        runBlocking {
            val expectedBeerList = listOf(someProduct, someProduct)
            givenNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProducts()
            assertEquals(Result.success(expectedBeerList), actual)
        }
    }

    @Test
    fun `Given Success response for OTHER  food, domain reuslt list is returned`() {
        runBlocking {
            val expectedBeerList = listOf(someProduct)
            givenOtherNetworkResponseOK()

            val actual = sut.getProducts()

            verify(apiService).getProducts()
            assertEquals(Result.success(expectedBeerList), actual)
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
            .willReturn(Calls.response(someBeerDataResults))
    }

    private fun givenOtherNetworkResponseOK() {
        given(apiService.getProducts())
            .willReturn(Calls.response(someOtherBeerDataResults))
    }

    private fun givenNetworkResponseKO() {
        given(apiService.getProducts())
            .willReturn(Calls.failure(Exception()))
    }

}