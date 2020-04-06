package com.example.ocadochallenge.repository

import com.example.brewdogbeers.repository.ProductsRespository
import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.someBeerModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
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
    fun `Given food name that hasn't been previously searched, beerList is getted from Network and stored Localy`() {
        runBlocking {
            val expected = listOf(someBeerModel)
            givenListBeerFromNetworkDatasource(expected)

            val actual = sut.getBeersForFood(ANY_FOOD)

            verify(networkDataSource).getBeersFromFood(ANY_FOOD)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given failure from Network, no data is stored localy`() {
        runBlocking {
            val expected: Result<List<ProductModel>> = Result.failure(failureException)
            givenFailureFromNetworkDatasource()

            val actual = sut.getBeersForFood(ANY_FOOD)


            verify(networkDataSource).getBeersFromFood(ANY_FOOD)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Given food name that  has been previously searched, data is returned from local data source`() {
        runBlocking {
            val expected = listOf(someBeerModel)

            val actual = sut.getBeersForFood(ANY_FOOD)

            verify(networkDataSource, never()).getBeersFromFood(ANY_FOOD)
            assert(actual.isSuccess)
        }
    }

    private suspend fun givenListBeerFromNetworkDatasource(productList: List<ProductModel>) {
        given(networkDataSource.getBeersFromFood(anyString())).willReturn(Result.success(productList))
    }

    private suspend fun givenFailureFromNetworkDatasource() {
        given(networkDataSource.getBeersFromFood(anyString())).willReturn(Result.failure(failureException))
    }



}