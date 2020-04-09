package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.usecase.GetProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor(
    private val view: ProductDetailContract.View,
    private val getProductUseCase: GetProductUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ProductDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun getProduct(id: Int) {
        launch {
            val result = withContext(ioDispatcher){ getProductUseCase(id) }
            result.fold(
                onSuccess = { view.showProduct(it) },
                onFailure = { view.showError() }
            )


        }
    }
}