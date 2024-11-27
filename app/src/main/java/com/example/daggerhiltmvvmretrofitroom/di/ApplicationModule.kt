package com.example.daggerhiltmvvmretrofitroom.di

import com.example.daggerhiltmvvmretrofitroom.IRepository
import com.example.daggerhiltmvvmretrofitroom.Repository
import com.example.daggerhiltmvvmretrofitroom.model.local.LocalRepository
import com.example.daggerhiltmvvmretrofitroom.model.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesRepository(localRepository: LocalRepository,remoteRepository: RemoteRepository): IRepository {
        return Repository(localRepository,remoteRepository)
    }


    // this only in the case of Interface and we need to make the class and function as abstract
    //in case of @Binds
//    @Binds
//    abstract fun bindRepository(localRepository: LocalRepository, remoteRepository: RemoteRepository):IRepository
}