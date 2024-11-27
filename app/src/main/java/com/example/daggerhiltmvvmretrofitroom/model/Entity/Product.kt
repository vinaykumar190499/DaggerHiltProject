package com.example.daggerhiltmvvmretrofitroom.model.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val modelName: String,
    val manufacturerYear: Int,
    val price: Double,
    val cpuModel: String,
    val hardDiskSize: String
)
