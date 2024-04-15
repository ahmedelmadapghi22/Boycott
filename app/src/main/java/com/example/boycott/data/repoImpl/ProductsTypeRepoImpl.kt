package com.example.boycott.data.repoImpl

import com.example.boycott.data.Result
import com.example.boycott.data.datasource.LocalProductTypeDataSource
import com.example.boycott.data.local.entity.ProductTypeEntity
import com.example.boycott.domain.repo.ProductsTypeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsTypeRepoImpl @Inject constructor(private val localProductTypeDataSource: LocalProductTypeDataSource) :
    ProductsTypeRepo {
    override suspend fun getProductsType(): Result<List<ProductTypeEntity>> {
        return localProductTypeDataSource.getProductsType()
    }
}