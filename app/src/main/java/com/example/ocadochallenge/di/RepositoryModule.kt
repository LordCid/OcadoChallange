package com.example.ocadochallenge.di

import com.example.ocadochallenge.repository.ProductRepository
import com.example.ocadochallenge.repository.ProductRepositoryImpl
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository

    @Binds
    fun bindProductNetworkDataSource(networkDataSource: ProductsNetworkDataSourceImpl): ProductsNetworkDataSource

}