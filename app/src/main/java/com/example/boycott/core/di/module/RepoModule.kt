package com.example.boycott.core.di.module

import com.example.boycott.data.datasource.LocalProductTypeDataSource
import com.example.boycott.data.datasource.LocalProductsBoycottDataSource
import com.example.boycott.data.repoImpl.BoycottProductsRepoImpl
import com.example.boycott.data.repoImpl.ProductsTypeRepoImpl
import com.example.boycott.domain.repo.BoycottProductsRepo
import com.example.boycott.domain.repo.ProductsTypeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Singleton
    @Provides
    fun provideProductsTypeModule(productTypeDataSource: LocalProductTypeDataSource): ProductsTypeRepo {
        return ProductsTypeRepoImpl(productTypeDataSource)
    }
    @Singleton
    @Provides
    fun provideBoycottProductsRepo(boycottDataSource: LocalProductsBoycottDataSource): BoycottProductsRepo {
        return BoycottProductsRepoImpl(boycottDataSource)
    }

}