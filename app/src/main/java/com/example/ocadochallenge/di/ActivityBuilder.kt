package com.example.ocadochallenge.di

import com.example.ocadochallenge.view.ProductDetailActivity
import com.example.ocadochallenge.view.ProductListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        DomainModule::class,
        RepositoryModule::class,
        ProductListProvidesModule::class
    ]
)
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [ProductListModule::class])
    fun bindProductListActivity(): ProductListActivity

    @ContributesAndroidInjector(modules = [ProductDetailModule::class])
    fun bindProductDetailActivity(): ProductDetailActivity
}