package com.example.boycott.data.repoImpl

import com.example.boycott.data.Result
import com.example.boycott.data.datasource.LocalProductTypeDataSource
import com.example.boycott.data.datasource.LocalProductsBoycottDataSource
import com.example.boycott.data.local.entity.BoycottProductEntity
import com.example.boycott.data.local.entity.ProductTypeEntity
import com.example.boycott.domain.repo.BoycottProductsRepo
import com.example.boycott.domain.repo.ProductsTypeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BoycottProductsRepoImpl @Inject constructor(private val localProductsBoycottDataSource: LocalProductsBoycottDataSource) :
    BoycottProductsRepo {


    override suspend fun getBoycottProducts(categoryId: Int): Result<List<BoycottProductEntity>> {
        return localProductsBoycottDataSource.getBoycottProducts(categoryId)
    }
}