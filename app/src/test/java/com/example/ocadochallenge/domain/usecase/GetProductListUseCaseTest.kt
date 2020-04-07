package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.GlobalConstants.ANY_OTHER_FOOD
import com.example.ocadochallenge.repository.ProductsRespository
import com.example.ocadochallenge.someProduct
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetProductListUseCaseTest {

    private val repository = mock<ProductsRespository>()

    private lateinit var sut: GetProductListUseCase

    @Before
    fun setUp() {
        sut = GetProductListUseCaseImpl(repository)
    }

    @Test
    fun `Given food name, should return Result`() {
        runBlocking {
            val expected = Result.success(listOf(someProduct))
            given(repository.getProductList()).willReturn(expected)

            val actual = sut(ANY_FOOD)

            verify(repository).getProductList()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Given OTHER food name, should return Result`() {
        runBlocking {
            val expected = Result.success(listOf(someProduct, someProduct))
            given(repository.getProductList()).willReturn(expected)

            val actual = sut(ANY_OTHER_FOOD)

            verify(repository).getProductList()
            assertEquals(expected, actual)
        }
    }
}