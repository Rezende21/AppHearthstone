package com.example.apphearthstone.di

import com.example.apphearthstone.data.remote.ServiceApi
import com.example.apphearthstone.repository.Repository
import com.example.apphearthstone.repository.RepositoryHearthstone
import com.example.apphearthstone.utis.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameApi() : ServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryHearthstone(
        api : ServiceApi
    ) = RepositoryHearthstone(api) as Repository
}