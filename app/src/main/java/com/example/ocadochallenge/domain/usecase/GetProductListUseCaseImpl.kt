package com.example.ocadochallenge.domain.usecase

import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetProductListUseCase {
    override suspend operator fun invoke(): Result<List<ProductCluster>> {
        return repository.getProductList()
    }
}