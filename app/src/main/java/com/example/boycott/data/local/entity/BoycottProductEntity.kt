package com.example.boycott.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.boycott.data.local.DBConstants

@Entity(
    tableName = DBConstants.table_boycott_products,
    foreignKeys = [ForeignKey(
        entity = ProductTypeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("categoryID"),
        onDelete = ForeignKey.NO_ACTION

    )]
)
data class BoycottProductEntity(
    @ColumnInfo(name = "categoryID")
    val categoryId: Int,
    @ColumnInfo(name = "boycottProductName")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "boycottProductID")
    val productID: Int,
)