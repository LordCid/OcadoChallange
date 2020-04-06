package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.ProductModel

interface ProductListContract {

    interface View{
        fun showResultList(productList: List<ProductModel>)
        fun showError()
    }

    interface Presenter{
        fun getBeerListSortByIncreasingABV(foodName: String)
        fun getBeerListSortByDecreasingABV(foodName: String)
    }
}