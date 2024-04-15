package com.example.boycott.data.datasource

import android.util.Log
import com.example.boycott.data.Result
import com.example.boycott.data.local.doa.ProductsTypeDao
import com.example.boycott.data.local.entity.ProductTypeEntity
import javax.inject.Inject

class LocalProductTypeDataSource @Inject constructor(private val productsTypeDao: ProductsTypeDao) {
    suspend fun getProductsType(): Result<List<ProductTypeEntity>> {
        return try {
            val list = productsTypeDao.getAllProductsType()
            if (list.isEmpty()) {
                Result.Error(Throwable("Products Type Not Found"))

            } else {
                Log.d("????????", "LocalProductTypeDataSource:${list}")
                Result.Success(list)
            }
        } catch (ex: Exception) {
            Result.Error(Throwable(ex.message))
        }
    }
}