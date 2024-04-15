package com.example.boycott.core.di.module

import android.content.Context
import androidx.room.Room
import com.example.boycott.data.local.DBConstants
import com.example.boycott.data.local.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MyDatabase::class.java, DBConstants.database_name)
            .createFromAsset("boycott_database.db").fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideProductsTypeDao(database: MyDatabase) = database.productTypeDao()

    @Singleton
    @Provides
    fun provideBoycottProductsTypeDao(database: MyDatabase) = database.boycottProductsDao()
}