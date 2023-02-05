package com.alireza.picture.domain.repository.favoritePhoto

import com.alireza.core.data.repository.DataModel
import com.alireza.picture.data.local.entity.favoritePhoto.FavoritePhotoEntity
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam
import com.alireza.picture.data.param.favoritePhoto.RemoveFavoritePhotoParam
import kotlinx.coroutines.flow.Flow


interface FavoritePhotoRepository {
    fun favoritePhotoList(): Flow<DataModel<List<PhotoDetailEntity>>>
    fun favoritePhoto(photo: FavoritePhotoParam)
    fun favoritePhotoIdList(): Flow<DataModel<List<FavoritePhotoEntity>>>
    fun removeFavoritePhoto(param: RemoveFavoritePhotoParam)
}