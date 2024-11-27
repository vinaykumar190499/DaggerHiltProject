package com.example.daggerhiltmvvmretrofitroom.model.local

import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product
import javax.inject.Inject

class LocalRepository @Inject constructor(private val appDatabase: AppDatabase) {
    fun saveProduct(product: Product) = appDatabase.productDao().insertNewProduct(product)
    fun getProducts():List<Product> = appDatabase.productDao().getProducts()
    fun searchProductInDb(name:String):Product = appDatabase.productDao().getProductByName(name)
}