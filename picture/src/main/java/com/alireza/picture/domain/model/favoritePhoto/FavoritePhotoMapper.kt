package com.alireza.picture.domain.model.favoritePhoto

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.picture.data.local.entity.favoritePhoto.FavoritePhotoEntity

class FavoritePhotoMapper : DomainModelMapper<FavoritePhoto, FavoritePhotoEntity> {
    override fun toDomainModel(dataModel: FavoritePhotoEntity): FavoritePhoto =
        FavoritePhoto(dataModel.id)
}