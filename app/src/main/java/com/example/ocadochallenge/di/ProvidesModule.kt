package com.example.ocadochallenge.di

import com.example.ocadochallenge.domain.ProductMapper
import com.example.ocadochallenge.domain.ProductMapperImp
import com.example.ocadochallenge.repository.rest.ApiService
import dagger.Module
import dagger.Provides

@Module
object ProvidesModule  {
    @Provides
    @JvmStatic
    fun providesApiService() = ApiService.create()

    @Provides
    @JvmStatic
    fun providesProductListMapper(): ProductMapper = ProductMapperImp()
}