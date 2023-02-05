package com.alireza.picture.domain.useCase.favoritePhoto

import com.alireza.core.domain.usecase.UseCase
import com.alireza.picture.data.param.favoritePhoto.RemoveFavoritePhotoParam
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import javax.inject.Inject

class FavoritePhotoRemoveUseCase @Inject constructor(private val favoritePhotoRepository: FavoritePhotoRepository) :
    UseCase<RemoveFavoritePhotoParam>() {

    override suspend fun execute(
        parameters: RemoveFavoritePhotoParam,
        onSuccess: (() -> Unit)?,
        onException: (() -> Unit)?
    ) {
        favoritePhotoRepository.removeFavoritePhoto(parameters)
    }
}