package com.example.boycott.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boycott.data.local.DBConstants

@Entity(tableName = DBConstants.table_products_type)
data class ProductTypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Int,
    @ColumnInfo(name = "type")
    @NonNull
    val type: String,

)