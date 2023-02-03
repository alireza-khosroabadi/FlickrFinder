package com.alireza.picture.di

import android.content.Context
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.database.PhotoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providePhotoDataBase(@ApplicationContext context: Context) =
        PhotoDataBase.getInstance(context = context)

    @Provides
    fun provideRecentPhotoDao(photoDataBase: PhotoDataBase) =
        photoDataBase.recentPhotoDao()


    @Provides
    fun provideSearchHistoryDao(photoDataBase: PhotoDataBase) =
        photoDataBase.searchHistoryDao()

    @Provides
    fun provideFavoritePhotoDao(photoDataBase: PhotoDataBase) =
        photoDataBase.favoritePhotoDao()

    @Provides
    fun provideUpdateTableDao(photoDataBase: PhotoDataBase) =
        photoDataBase.updateTableDao()

}