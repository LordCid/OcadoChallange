package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.Product

interface GetProductUseCase {
    suspend operator fun invoke(id: Int): Result<Product>
}