package com.alireza.picture.data.param.favoritePhoto

import com.alireza.core.data.param.ParamModel
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity

data class FavoritePhotoParam(
    val id: String,
    val ownerName: String,
    var location: String,
    val title: String,
    var description: String,
    var postedDate: String,
    val views: Int,
    val commentsCount: Int,
    val url: String,
    val isFavorite: Boolean
) : ParamModel

fun FavoritePhotoParam.toPhotoDetailEntity(): PhotoDetailEntity = with(this) {
    PhotoDetailEntity(
        id, ownerName, location, title, description, postedDate, views, commentsCount, url
    )
}
