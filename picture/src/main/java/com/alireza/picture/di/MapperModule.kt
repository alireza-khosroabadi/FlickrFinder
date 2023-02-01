package com.alireza.picture.di

import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun provideRecentPhotoEntityMapper():RecentPhotoMapper = RecentPhotoMapper()
}