package com.alireza.picture.domain.model.photoDetail

import com.alireza.core.domain.model.DomainModel
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam

data class PhotoDetail(
    val id: String,
    var isFavorite: Boolean,
    val ownerName: String,
    var location: String,
    val title: String,
    var description: String,
    var postedDate: String,
    val views: Int,
    val commentsCount: Int
) : DomainModel {
    var url: String = ""
}

fun PhotoDetail.toFavoritePhotoParam(): FavoritePhotoParam = with(this) {
    FavoritePhotoParam(
        id,
        ownerName,
        location,
        title,
        description,
        postedDate,
        views,
        commentsCount,
        url,
        isFavorite
    )
}