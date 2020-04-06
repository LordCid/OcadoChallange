package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.GlobalConstants.ANY_FOOD
import com.example.ocadochallenge.domain.model.ProductModel
import com.example.ocadochallenge.domain.usecase.GetProductListUseCase
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
import org.mockito.ArgumentMatchers.anyString

class ProductListPresenterTest {

    private val view = mock<ProductListContract.View>()
    private val getBeersForFoodUseCase = mock<GetProductListUseCase>()

    private lateinit var sut: ProductListContract.Presenter


    val lowAbvBeerModel = ProductModel(
        name = "lowAbvBeerName",
        tagline = "tagline",
        description = "description",
        image = "image",
        abv = 4.0f
    )

    val mediumAbvBeerModel = ProductModel(
        name = "mediumAbvBeerName",
        tagline = "tagline",
        description = "description",
        image = "image",
        abv = 10.0f
    )

    val mediumHighAbvBeerModel = ProductModel(
        name = "mediumHighAbvBeerName",
        tagline = "tagline",
        description = "description",
        image = "image",
        abv = 20.0f
    )

    val highAbvBeerModel = ProductModel(
        name = "highAbvBeerName",
        tagline = "tagline",
        description = "description",
        image = "image",
        abv = 40.0f
    )

    @ExperimentalCoroutinesApi
    private var dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = ProductListPresenter(view, getBeersForFoodUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given list getted, when sorting by increasing abv beer, correct list is shown in the UI`() {
        runBlocking {
            val expectedSortedList = listOf(
                lowAbvBeerModel,
                mediumAbvBeerModel,
                mediumHighAbvBeerModel,
                highAbvBeerModel
            )
            val resultList = listOf(
                mediumHighAbvBeerModel,
                mediumAbvBeerModel,
                highAbvBeerModel,
                lowAbvBeerModel
            )
            givenSuccessResultWithValues(resultList)

            sut.getBeerListSortByIncreasingABV(ANY_FOOD)

            val inOrder = inOrder(view, getBeersForFoodUseCase)
            inOrder.verify(getBeersForFoodUseCase).invoke(ANY_FOOD)
            inOrder.verify(view).showResultList(expectedSortedList)
        }
    }


    @Test
    fun `Given list getted, when sorting by decreasing abv beer, correct list is shown in the UI`() {
        runBlocking {
            val expectedSortedList = listOf(
                highAbvBeerModel,
                mediumHighAbvBeerModel,
                mediumAbvBeerModel,
                lowAbvBeerModel
            )
            val resultList = listOf(
                mediumHighAbvBeerModel,
                mediumAbvBeerModel,
                highAbvBeerModel,
                lowAbvBeerModel
            )
            givenSuccessResultWithValues(resultList)

            sut.getBeerListSortByDecreasingABV(ANY_FOOD)

            val inOrder = inOrder(view, getBeersForFoodUseCase)
            inOrder.verify(getBeersForFoodUseCase).invoke(ANY_FOOD)
            inOrder.verify(view).showResultList(expectedSortedList)
        }
    }

    @Test
    fun `Given failure getted when input search term, error is shown in UI`() {
        runBlocking {
            givenFailureResult()

            sut.getBeerListSortByIncreasingABV(ANY_FOOD)

            val inOrder = inOrder(view, getBeersForFoodUseCase)
            inOrder.verify(getBeersForFoodUseCase).invoke(ANY_FOOD)
            inOrder.verify(view).showError()
        }
    }

    private suspend fun givenFailureResult() {
        given(getBeersForFoodUseCase.invoke(anyString()))
            .willReturn(Result.failure(mock<Exception>()))
    }

    private suspend fun givenSuccessResultWithValues(beerlist: List<ProductModel>) {
        given(getBeersForFoodUseCase.invoke(anyString()))
            .willReturn(Result.success(beerlist))
    }
}