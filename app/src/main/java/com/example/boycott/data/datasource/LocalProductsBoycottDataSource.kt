package com.example.boycott.data.datasource

import android.util.Log
import com.example.boycott.data.Result
import com.example.boycott.data.local.doa.BoycottProductsDao
import com.example.boycott.data.local.doa.ProductsTypeDao
import com.example.boycott.data.local.entity.BoycottProductEntity
import com.example.boycott.data.local.entity.ProductTypeEntity
import javax.inject.Inject

class LocalProductsBoycottDataSource @Inject constructor(private val boycottProductsDao: BoycottProductsDao) {
    suspend fun getBoycottProducts(id:Int): Result<List<BoycottProductEntity>> {
        return try {
            val list = boycottProductsDao.getAllProductsByCategory(id)
            Log.d("????????", "LocalProductsBoycottDataSource:${list}")
            Result.Success(list)
        } catch (ex: Exception) {
            Result.Error(Throwable(ex.message))
        }
    }
}