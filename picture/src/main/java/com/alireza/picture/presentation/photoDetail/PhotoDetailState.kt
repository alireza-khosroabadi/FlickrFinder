package com.alireza.picture.presentation.photoDetail

import com.alireza.picture.domain.model.photoDetail.PhotoDetail

sealed class PhotoDetailState
object Loading : PhotoDetailState()
data class PhotoState(val data: PhotoDetail) : PhotoDetailState()
data class IsFavoritePhoto(val isFavorite:Boolean):PhotoDetailState()
