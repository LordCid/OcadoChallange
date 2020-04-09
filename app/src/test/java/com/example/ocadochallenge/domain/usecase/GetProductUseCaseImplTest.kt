package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.getProductById
import com.example.ocadochallenge.repository.ProductRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProductUseCaseTest {

    private lateinit var sut: GetProductUseCase

    private val repository = mock<ProductRepository>()

    @Before
    fun setUp() {
        sut = GetProductUseCaseImpl(repository)
    }

    @Test
    fun `Should return result from product repository`() {
        runBlocking {
            val someId = 1234
            val expected = Result.success(getProductById(someId))
            given(repository.getProduct(someId)).willReturn(expected)

            val actual = sut.invoke(someId)

            verify(repository).getProduct(someId)
            assertEquals(expected, actual)
        }
    }
}