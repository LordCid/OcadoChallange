package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.usecase.GetProductUseCase
import com.example.ocadochallenge.getProductById
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductDetailPresenterTest {

    private lateinit var sut: ProductDetailContract.Presenter

    private val view = mock<ProductDetailContract.View>()
    private val getProductUseCase = mock<GetProductUseCase>()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = ProductDetailPresenter(view, getProductUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    private val someId = 1234
    private val someOtherId = 5678


    @Test
    fun `Given product Id, should get product and show it in UI`() {
        runBlocking {
            val expectedProduct = getProductById(someId)
            given(getProductUseCase.invoke(someId))
                .willReturn(Result.success(expectedProduct))

            sut.getProduct(someId)

            val inOrder = inOrder(getProductUseCase, view)
            inOrder.verify(getProductUseCase).invoke(someId)
            inOrder.verify(view).showProduct(expectedProduct)
        }
    }

    @Test
    fun `Given OTHER product Id, should get product and show it in UI`() {
        runBlocking {
            val expectedProduct = getProductById(someOtherId)
            givenGetConcreteProduct(expectedProduct)

            sut.getProduct(someOtherId)

            val inOrder = inOrder(getProductUseCase, view)
            inOrder.verify(getProductUseCase).invoke(someOtherId)
            inOrder.verify(view).showProduct(expectedProduct)
        }
    }


    @Test
    fun `Given failure when getting product list, error is shown in the UI`() {
        runBlocking {
            givenGetProductFailure()

            sut.getProduct(someId)

            val inOrder = inOrder(getProductUseCase, view)
            inOrder.verify(getProductUseCase).invoke(someId)
            inOrder.verify(view).showError()
        }
    }

    private suspend fun givenGetConcreteProduct(expectedProduct: Product) {
        given(getProductUseCase.invoke(someOtherId))
            .willReturn(Result.success(expectedProduct))
    }

    private suspend fun givenGetProductFailure() {
        given(getProductUseCase.invoke(someId))
            .willReturn(Result.failure(mock<Exception>()))
    }
}