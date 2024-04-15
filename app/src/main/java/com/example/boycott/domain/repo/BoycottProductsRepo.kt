package com.example.boycott.domain.repo

import com.example.boycott.data.Result
import com.example.boycott.data.local.entity.BoycottProductEntity
import com.example.boycott.data.local.entity.ProductTypeEntity
import kotlinx.coroutines.flow.Flow

interface BoycottProductsRepo {
    suspend fun getBoycottProducts(categoryId: Int): Result<List<BoycottProductEntity>>
}