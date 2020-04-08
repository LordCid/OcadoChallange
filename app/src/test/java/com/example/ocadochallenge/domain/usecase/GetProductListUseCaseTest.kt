package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.ProductsRespository
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
    fun `Should return result from product respository`() {
        runBlocking {
            val expected = Result.success(emptyList<ProductCluster>())
            given(repository.getProductList()).willReturn(expected)

            val actual = sut.invoke()

            verify(repository).getProductList()
            assertEquals(expected, actual)
        }
    }
}