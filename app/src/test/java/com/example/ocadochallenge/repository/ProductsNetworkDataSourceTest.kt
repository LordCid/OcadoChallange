package com.example.ocadochallenge.repository

import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.GlobalConstants.ANY_OTHER_FOOD
import com.example.ocadochallenge.domain.ProductDomainMapper
import com.example.brewdogbeers.repository.rest.ApiService
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSource
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSourceImpl
import com.example.ocadochallenge.someBeerModel
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

            sut.getBeersFromFood(ANY_FOOD)

            verify(apiService).getBeersForFood(ANY_FOOD)
        }
    }

    @Test
    fun `Given Success request, domain result List is returned`() {
        runBlocking {
            val expectedBeerList = listOf(someBeerModel, someBeerModel)
            givenNetworkResponseOK()

            val actual = sut.getBeersFromFood(ANY_FOOD)

            verify(apiService).getBeersForFood(ANY_FOOD)
            assertEquals(Result.success(expectedBeerList), actual)
        }
    }

    @Test
    fun `Given Success response for OTHER  food, domain reuslt list is returned`() {
        runBlocking {
            val expectedBeerList = listOf(someBeerModel)
            givenOtherNetworkResponseOK()

            val actual = sut.getBeersFromFood(ANY_OTHER_FOOD)

            verify(apiService).getBeersForFood(ANY_OTHER_FOOD)
            assertEquals(Result.success(expectedBeerList), actual)
        }
    }

    @Test
    fun `Should return failure when response is Error`() {
        runBlocking {
            givenNetworkResponseKO()

            val result = sut.getBeersFromFood(ANY_FOOD)
            assert(result.isFailure)
        }
    }


    private fun givenNetworkResponseOK() {
        given(apiService.getBeersForFood(ANY_FOOD))
            .willReturn(Calls.response(someBeerDataResults))
    }

    private fun givenOtherNetworkResponseOK() {
        given(apiService.getBeersForFood(ANY_OTHER_FOOD))
            .willReturn(Calls.response(someOtherBeerDataResults))
    }

    private fun givenNetworkResponseKO() {
        given(apiService.getBeersForFood(ANY_OTHER_FOOD))
            .willReturn(Calls.failure(Exception()))
    }

}