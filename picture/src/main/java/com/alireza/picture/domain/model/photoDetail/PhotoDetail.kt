package com.alireza.picture.domain.model.photoDetail

import com.alireza.core.domain.model.DomainModel

data class PhotoDetail(
    val id           : String,
    val isFavorite   : Int,
    val ownerName    : String,
    var location     : String,
    val title        : String,
    var description  : String,
    var postedDate   : String,
    val views        : Int,
    val commentsCount: Int
):DomainModel {
    var url:String =""
}