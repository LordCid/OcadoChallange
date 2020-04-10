package com.example.ocadochallenge.di

import com.example.ocadochallenge.domain.ProductMapperImp
import com.example.ocadochallenge.domain.ProductMapper
import com.example.ocadochallenge.domain.imageloader.GlideImplementation
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.presenter.ProductListContract
import com.example.ocadochallenge.presenter.ProductListPresenter
import com.example.ocadochallenge.repository.rest.ApiService
import com.example.ocadochallenge.view.ProductListActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ProductListModule {

    @Binds
    fun bindView(view: ProductListActivity): ProductListContract.View

    @Binds
    fun bindPresenter(presenter: ProductListPresenter): ProductListContract.Presenter
}

