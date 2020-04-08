package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.ProductCluster

interface ProductListContract {

    interface View {
        fun showResultList(productList: List<ProductCluster>)
        fun showError()
    }

    interface Presenter {
        fun getProductList()
    }
}