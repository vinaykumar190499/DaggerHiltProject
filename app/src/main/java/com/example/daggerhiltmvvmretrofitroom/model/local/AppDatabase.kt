package com.example.daggerhiltmvvmretrofitroom.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun productDao():ProductDao
}