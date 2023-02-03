package com.alireza.picture.di

import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.dao.updateTable.UpdateTableDao
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
        connectionCheck: NetworkConnectivity,
        apiService: PictureApiService,
        recentPhotoDao: RecentPhotoDao,
        updateTableDao: UpdateTableDao,
        recentPhotoMapper: RecentPhotoEntityMapper
    ): RecentPhotoRepository =
        RecentPhotoRepositoryImpl(connectionCheck,apiService, recentPhotoDao, updateTableDao, recentPhotoMapper )

    @Provides
    fun provideSearchPhotoRepository(
        internetConnection: NetworkConnectivity,
        apiService: PictureApiService,
        searchHistoryDao: SearchHistoryDao
    ): SearchPhotoRepository =
        SearchPhotoRepositoryImpl(internetConnection, apiService, searchHistoryDao)

    @Provides
    fun provideSearchHistoryRepository(
        searchHistoryDao: SearchHistoryDao
    ): SearchHistoryRepository =
        SearchHistoryRepositoryImpl(searchHistoryDao)

    @Provides
    fun providePhotoDetailRepository(
        internetConnection: NetworkConnectivity,
        apiService: PictureApiService
    ): PhotoDetailRepository =
        PhotoDetailRepositoryImpl(internetConnection,apiService)

    @Provides
    fun provideFavoritePhotoRepository(
        favoritePhotoDao: FavoritePhotoDao
    ): FavoritePhotoRepository =
        FavoritePhotoRepositoryImpl(favoritePhotoDao)

}