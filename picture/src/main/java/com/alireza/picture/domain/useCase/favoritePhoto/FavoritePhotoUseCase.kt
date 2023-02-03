package com.alireza.picture.domain.useCase.favoritePhoto

import com.alireza.core.domain.usecase.UseCase
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import javax.inject.Inject

class FavoritePhotoUseCase @Inject constructor(private val favoritePhotoRepository: FavoritePhotoRepository) :
    UseCase<FavoritePhotoParam>() {
    override suspend fun execute(
        parameters: FavoritePhotoParam,
        onSuccess: (() -> Unit)?,
        onException: (() -> Unit)?
    ) {
        favoritePhotoRepository.favoritePhoto(parameters)
    }
}