package com.alireza.picture.presentation.favoritePhoto

import com.alireza.picture.domain.model.photoDetail.PhotoDetail

sealed class FavoritePhotoState
object Loading: FavoritePhotoState()
data class FavoritePhotoList(val data: List<PhotoDetail>): FavoritePhotoState()
