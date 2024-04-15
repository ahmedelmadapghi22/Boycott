package com.example.boycott.data.local.doa

import androidx.room.Dao
import androidx.room.Query
import com.example.boycott.data.Result
import com.example.boycott.data.local.entity.ProductTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsTypeDao {
    @Query("SELECT * FROM products_types")
    suspend fun getAllProductsType(): List<ProductTypeEntity>
}