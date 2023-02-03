package com.alireza.picture.di

import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntityMapper
import com.alireza.picture.domain.model.favoritePhoto.FavoritePhotoMapper
import com.alireza.picture.domain.model.photoDetail.PhotoDetailEntityMapper
import com.alireza.picture.domain.model.photoDetail.PhotoDetailMapper
import com.alireza.picture.domain.model.recentPhoto.RecentPhotoMapper
import com.alireza.picture.domain.model.searchHistory.SearchHistoryMapper
import com.alireza.picture.domain.model.searchPhoto.SearchPhotoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun provideRecentPhotoEntityMapper(): RecentPhotoEntityMapper = RecentPhotoEntityMapper()

    @Provides
    fun provideRecentPhotoMapper(): RecentPhotoMapper = RecentPhotoMapper()

    @Provides
    fun provideSearchPhotoMapper(): SearchPhotoMapper = SearchPhotoMapper()

    @Provides
    fun provideSearchHistoryMapper(): SearchHistoryMapper = SearchHistoryMapper()

    @Provides
    fun providePhotoDetailMapper(): PhotoDetailMapper = PhotoDetailMapper()

    @Provides
    fun provideFavoritePhotoMapper(): FavoritePhotoMapper = FavoritePhotoMapper()

    @Provides
    fun providePhotoDetailEntityMapper(): PhotoDetailEntityMapper = PhotoDetailEntityMapper()

}