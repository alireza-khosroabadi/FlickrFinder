package com.alireza.flickrfinder.config

import android.content.Context
import androidx.room.Room
import com.alireza.picture.data.local.database.PhotoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InMemoryDatabase {

    @Provides
    fun createDatabase(@ApplicationContext context: Context):PhotoDataBase{
        return Room.inMemoryDatabaseBuilder(context, PhotoDataBase::class.java).build()
    }

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