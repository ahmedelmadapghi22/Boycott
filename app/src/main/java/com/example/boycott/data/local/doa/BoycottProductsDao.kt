package com.example.boycott.data.local.doa

import androidx.room.Dao
import androidx.room.Query
import com.example.boycott.data.Result
import com.example.boycott.data.local.entity.BoycottProductEntity
import com.example.boycott.data.local.entity.ProductTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoycottProductsDao {
    @Query("SELECT * FROM boycott_products WHERE categoryID=:id")
    suspend fun getAllProductsByCategory(id:Int): List<BoycottProductEntity>
}