package com.example.boycott.core.di.module

import com.example.boycott.domain.usecase.CheckProductBarcodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Singleton
    @Provides
    fun provideCheckProductBarcodeUseCase() = CheckProductBarcodeUseCase()


}