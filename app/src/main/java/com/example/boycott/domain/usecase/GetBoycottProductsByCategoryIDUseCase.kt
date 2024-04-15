package com.example.boycott.domain.usecase

import android.util.Log
import com.example.boycott.data.Result
import com.example.boycott.domain.model.BoycottProduct
import com.example.boycott.domain.repo.BoycottProductsRepo
import javax.inject.Inject

class GetBoycottProductsByCategoryIDUseCase @Inject constructor(private val boycottProductsRepo: BoycottProductsRepo) {
    suspend operator fun invoke(categoryID: Int): Result<List<BoycottProduct>> {
        return when (val result = boycottProductsRepo.getBoycottProducts(categoryID)) {
            is Result.Success -> {
                val entities = result.data
                if (entities.isEmpty()) {
                    Result.Success(emptyList())
                } else {
                    val boycottProducts = entities.map { entity ->
                        BoycottProduct(entity.categoryId, entity.name,entity.productID)

                    }
                    Log.d("????????", "GetBoycottProductsByCategoryIDUseCase:${boycottProducts}")
                    Result.Success(boycottProducts)
                }


            }

            is Result.Error -> {
                Result.Error(result.errMsg)
            }
        }
    }

}