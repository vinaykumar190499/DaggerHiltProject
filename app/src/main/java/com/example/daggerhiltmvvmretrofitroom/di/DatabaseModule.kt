package com.example.daggerhiltmvvmretrofitroom.di

import android.content.Context
import androidx.room.Room
import com.example.daggerhiltmvvmretrofitroom.model.local.AppDatabase
import com.example.daggerhiltmvvmretrofitroom.model.local.LocalRepository
import com.example.daggerhiltmvvmretrofitroom.model.local.ProductDao
import com.example.daggerhiltmvvmretrofitroom.model.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context:Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "MyProductDb"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesProductDao(appDatabase: AppDatabase):ProductDao{
        return appDatabase.productDao()
    }

    @Provides
    @Singleton
    fun providesLocalRepository(appDatabase: AppDatabase):LocalRepository{
        return LocalRepository(appDatabase)
    }


}