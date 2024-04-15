package com.example.boycott.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boycott.data.local.doa.BoycottProductsDao
import com.example.boycott.data.local.doa.ProductsTypeDao
import com.example.boycott.data.local.entity.BoycottProductEntity
import com.example.boycott.data.local.entity.ProductTypeEntity

@Database(entities = [ProductTypeEntity::class,BoycottProductEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun productTypeDao(): ProductsTypeDao
    abstract fun boycottProductsDao(): BoycottProductsDao


}