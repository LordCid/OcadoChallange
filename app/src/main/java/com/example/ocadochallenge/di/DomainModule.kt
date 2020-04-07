package com.example.ocadochallenge.di

import com.example.ocadochallenge.domain.usecase.GetProductListUseCase
import com.example.ocadochallenge.domain.usecase.GetProductListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindGetProductListUseCase(useCase: GetProductListUseCaseImpl): GetProductListUseCase
}