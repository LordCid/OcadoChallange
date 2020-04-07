package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.ProductCluster

interface GetProductListUseCase {
    suspend operator fun invoke(): Result<List<ProductCluster>>
}