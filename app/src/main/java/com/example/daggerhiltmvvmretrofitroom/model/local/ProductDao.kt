package com.example.daggerhiltmvvmretrofitroom.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductData

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewProduct(product:Product)

    @Query("SELECT * from product")
    fun getProducts():List<Product>

    @Query("SELECT * FROM PRODUCT WHERE modelName= :name")
    fun getProductByName(
        name:String
    ):Product
}