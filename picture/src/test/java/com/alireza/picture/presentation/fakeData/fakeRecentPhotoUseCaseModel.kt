package com.alireza.picture.presentation.fakeData

import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityList
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import com.alireza.picture.domain.model.recentPhoto.RecentPhotoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeRecentPhotoUseCaseModel: Flow<UseCaseModel.Success<List<RecentPhoto>>> =
    flowOf(UseCaseModel.Success(fakeRecentPhotoEntityList.map { RecentPhotoMapper().toDomainModel(it) }))

val fakeRecentPhotoUseCaseErrorModel: Flow<UseCaseModel.Error<List<RecentPhoto>>> =
    flowOf(UseCaseModel.Error(null, 0,"fail"))
