package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor (private val repository: ProductRepository) : GetProductUseCase {
    override suspend fun invoke(id: Int): Result<Product> {
        return repository.getProduct(id)
    }
}