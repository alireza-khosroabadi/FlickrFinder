package com.alireza.picture.di

import com.alireza.picture.data.remote.api.PictureApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule{

    @Singleton
    @Provides
    fun providePictureApiService(retrofit: Retrofit): PictureApiService =
        retrofit.create(PictureApiService::class.java)
}