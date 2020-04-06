package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.ProductModel
import com.example.brewdogbeers.repository.ProductsRespository
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val repository: ProductsRespository
) : GetProductListUseCase {
    override suspend operator fun invoke(foodName: String): Result<List<ProductModel>> {
        return repository.getBeersForFood(foodName)
    }
}