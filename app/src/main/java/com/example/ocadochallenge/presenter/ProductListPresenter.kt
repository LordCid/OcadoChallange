package com.example.ocadochallenge.presenter

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


    override fun getProductList() {
        launch {
            val results = withContext(ioDispatcher) {
                getProductListUseCase()
            }
            results.fold(
                onSuccess = { view.showResultList(it)},
                onFailure = { view.showError() }
            )
        }
    }
}