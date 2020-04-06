package com.example.ocadochallenge.di

import com.example.brewdogbeers.repository.ProductsRespository
import com.example.ocadochallenge.repository.ProductsRespositoryImpl
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSource
import com.example.brewdogbeers.repository.rest.ProductsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindBeerRepository(repository: ProductsRespositoryImpl): ProductsRespository

    @Binds
    fun bindBeerNetworkDataSource(networkDataSource: ProductsNetworkDataSourceImpl): ProductsNetworkDataSource


}