package com.alireza.picture.presentation.fakeData

import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.data.fakeData.fakeSearchHistoryEntityList
import com.alireza.picture.data.fakeData.fakeSearchPhotoEntityResponse
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.model.searchHistory.SearchHistoryMapper
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto
import com.alireza.picture.domain.model.searchPhoto.SearchPhotoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeSearchPhotoUseCaseModel: Flow<UseCaseModel.Success<List<SearchPhoto>>> =
    flowOf(UseCaseModel.Success(fakeSearchPhotoEntityResponse.photos.photo.map { SearchPhotoMapper().toDomainModel(it) }))

val fakeSearchPhotoUseCaseErrorModel: Flow<UseCaseModel.Error<List<SearchPhoto>>> =
    flowOf(UseCaseModel.Error(null, 0,"fail"))

val fakeSearchHistoryUseCaseModel: Flow<UseCaseModel.Success<List<SearchHistory>>> =
    flowOf(UseCaseModel.Success(fakeSearchHistoryEntityList.map { SearchHistoryMapper().toDomainModel(it) }))
