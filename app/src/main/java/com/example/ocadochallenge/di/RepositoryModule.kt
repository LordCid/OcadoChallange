package com.example.ocadochallenge.di

import com.example.ocadochallenge.repository.ProductsRespository
import com.example.ocadochallenge.repository.ProductsRespositoryImpl
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSource
import com.example.ocadochallenge.repository.rest.ProductsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductRepository(repository: ProductsRespositoryImpl): ProductsRespository

    @Binds
    fun bindProductNetworkDataSource(networkDataSource: ProductsNetworkDataSourceImpl): ProductsNetworkDataSource

}