package com.alireza.picture.data.local.entity.photoDetail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alireza.core.data.local.entity.EntityModel

@Entity(tableName = "favorite_photo_table")
data class PhotoDetailEntity(
    @PrimaryKey val id : String,
    val ownerName    : String,
    var location     : String,
    val title        : String,
    var description  : String,
    var postedDate   : String,
    val views        : Int,
    val commentsCount: Int,
    val url          : String
): EntityModel