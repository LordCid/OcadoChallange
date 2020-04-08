package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.domain.usecase.GetProductListUseCase
import com.example.ocadochallenge.someProduct
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
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
import org.mockito.Mockito.inOrder

class ProductListPresenterTest {

    private val view = mock<ProductListContract.View>()
    private val getProductListUseCase = mock<GetProductListUseCase>()

    private lateinit var sut: ProductListContract.Presenter

    @ExperimentalCoroutinesApi
    private var dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = ProductListPresenter(view, getProductListUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given product list getted, it is shown into UI`() {
        runBlocking {
            val someCluster = ProductCluster(
                tag = "someTag",
                products = listOf(someProduct, someProduct)
            )
            val expectedList = listOf(someCluster)
            givenSuccessResultWithValues(expectedList)

            sut.getProductList()

            val inOrder = inOrder(view, getProductListUseCase)
            inOrder.verify(getProductListUseCase).invoke()
            inOrder.verify(view).showResultList(expectedList)
        }
    }

    @Test
    fun `Given failure when getting product list, error is shown in the UI`() {
        runBlocking {
            givenFailureResult()

            sut.getProductList()

            val inOrder = inOrder(view, getProductListUseCase)
            inOrder.verify(getProductListUseCase).invoke()
            inOrder.verify(view).showError()
        }
    }



    private suspend fun givenSuccessResultWithValues(list: List<ProductCluster>) {
        given(getProductListUseCase.invoke()).willReturn(Result.success(list))
    }

    private suspend fun givenFailureResult() {
        given(getProductListUseCase.invoke()).willReturn(Result.failure(mock<Exception>()))
    }
}