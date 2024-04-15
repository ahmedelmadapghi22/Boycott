package com.example.boycott.domain.usecase

import android.util.Log
import com.example.boycott.data.Result
import com.example.boycott.domain.model.ProductType
import com.example.boycott.domain.repo.ProductsTypeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsTypeUseCase @Inject constructor(private val productsTypeRepo: ProductsTypeRepo) {
    suspend operator fun invoke(): Result<List<ProductType>> {
        return when (val result = productsTypeRepo.getProductsType()) {
            is Result.Success -> {
                val entities = result.data
                val productsTypes = entities.map { entity ->
                    ProductType(entity.id, entity.type)

                }
                Log.d("????????", "GetProductsTypeUseCase:${productsTypes}")
                Result.Success(productsTypes)

            }

            is Result.Error -> {
                Result.Error(result.errMsg)
            }
        }
    }

}