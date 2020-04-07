package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListPresenter @Inject constructor(
    private val view: ProductListContract.View,
    private val getProductListUseCase: GetProductListUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ProductListContract.Presenter, CoroutineScope by MainScope() {
    override fun getBeerListSortByIncreasingABV(foodName: String) {
        launchAsyncGetProcess(foodName) {
            view.showResultList(sortByIncreasingABV(it))
        }
    }

    override fun getBeerListSortByDecreasingABV(foodName: String) {
        launchAsyncGetProcess(foodName) { view.showResultList(sortByDecreasingABV(it)) }
    }

    private fun launchAsyncGetProcess(foodName: String, block: (List<ProductCluster>) -> Unit){
        launch {
            val results = withContext(ioDispatcher) {
                getProductListUseCase()
            }
            results.fold(
                onSuccess = block,
                onFailure = { view.showError() }
            )
        }
    }

    private fun sortByIncreasingABV(productList: List<ProductCluster>): List<ProductCluster> {
//        return productList.sortedBy { it.abv }
        return emptyList()
    }

    private fun sortByDecreasingABV(productList: List<ProductCluster>): List<ProductCluster> {
//        return productList.sortedByDescending { it.abv }
        return emptyList()
    }

}