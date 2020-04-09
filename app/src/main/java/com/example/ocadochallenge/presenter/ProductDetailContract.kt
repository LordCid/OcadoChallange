package com.example.ocadochallenge.presenter

import com.example.ocadochallenge.domain.model.Product

interface ProductDetailContract {

    interface View{
        fun showProduct(product: Product)
        fun showError()
    }

    interface Presenter {
        fun getProduct(id: Int)
    }
}