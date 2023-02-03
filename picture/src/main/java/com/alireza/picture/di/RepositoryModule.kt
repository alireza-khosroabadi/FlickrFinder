package com.alireza.picture.di

import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntityMapper
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.repository.favoritePhoto.FavoritePhotoRepositoryImpl
import com.alireza.picture.data.repository.photoDetail.PhotoDetailRepositoryImpl
import com.alireza.picture.data.repository.recentPhoto.RecentPhotoRepositoryImpl
import com.alireza.picture.data.repository.searchHistory.SearchHistoryRepositoryImpl
import com.alireza.picture.data.repository.searchPhoto.SearchPhotoRepositoryImpl
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import com.alireza.picture.domain.repository.photoDetail.PhotoDetailRepository
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
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
        RecentPhotoRepositoryImpl(apiService, recentPhotoDao, recentPhotoMapper)

    @Provides
    fun provideSearchPhotoRepository(
        apiService: PictureApiService,
        searchHistoryDao: SearchHistoryDao
    ): SearchPhotoRepository =
        SearchPhotoRepositoryImpl(apiService, searchHistoryDao)

    @Provides
    fun provideSearchHistoryRepository(
        searchHistoryDao: SearchHistoryDao
    ): SearchHistoryRepository =
        SearchHistoryRepositoryImpl(searchHistoryDao)

    @Provides
    fun providePhotoDetailRepository(
        apiService: PictureApiService
    ): PhotoDetailRepository =
        PhotoDetailRepositoryImpl(apiService)

    @Provides
    fun provideFavoritePhotoRepository(
        favoritePhotoDao: FavoritePhotoDao
    ): FavoritePhotoRepository =
        FavoritePhotoRepositoryImpl(favoritePhotoDao)

}