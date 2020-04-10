package com.example.ocadochallenge.di

import com.example.ocadochallenge.domain.imageloader.GlideImplementation
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.presenter.ProductDetailContract
import com.example.ocadochallenge.presenter.ProductDetailPresenter
import com.example.ocadochallenge.view.ProductDetailActivity
import dagger.Binds
import dagger.Module

@Module
interface ProductDetailModule{

    @Binds
    fun bindView(view: ProductDetailActivity): ProductDetailContract.View

    @Binds
    fun bindPresenter(presenter: ProductDetailPresenter): ProductDetailContract.Presenter

//    @Binds
//    fun bindImagesLoader(images: GlideImplementation): ImagesLoader
}