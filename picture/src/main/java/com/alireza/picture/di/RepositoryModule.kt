package com.alireza.picture.di

import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntityMapper
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.repository.recentPhoto.RecentPhotoRepositoryImpl
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecentPhotoRepository(
        apiService: PictureApiService,
        recentPhotoDao: RecentPhotoDao,
        recentPhotoMapper: RecentPhotoEntityMapper
    ): RecentPhotoRepository =
        RecentPhotoRepositoryImpl(apiService,recentPhotoDao, recentPhotoMapper)
}