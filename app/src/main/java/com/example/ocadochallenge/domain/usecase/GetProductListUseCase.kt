package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.ProductModel

interface GetProductListUseCase {
    suspend operator fun invoke(foodName: String): Result<List<ProductModel>>
}