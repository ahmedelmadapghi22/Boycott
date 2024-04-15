package com.example.boycott.domain.repo

import com.example.boycott.data.Result
import com.example.boycott.data.local.entity.ProductTypeEntity
import kotlinx.coroutines.flow.Flow

interface ProductsTypeRepo {
    suspend fun getProductsType(): Result<List<ProductTypeEntity>>
}