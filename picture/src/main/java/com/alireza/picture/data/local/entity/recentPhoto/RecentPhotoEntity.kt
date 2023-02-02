package com.alireza.picture.data.local.entity.recentPhoto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alireza.core.data.local.entity.EntityModel

@Entity(tableName = "recent_photo_table")
data class RecentPhotoEntity(
    val id: String = "",
    val owner: String = "",
    val ownerName: String = "",
    val secret: String = "",
    val server: String = "",
    val farm: Int = 0,
    val title: String = "",
    val isPublic: Boolean = false,
    val isFriend: Boolean = false,
    val isFamily: Boolean = false,
    val url: String = "",
    val urlLarge: String = "",
    val height: Int = 0,
    val width: Int = 0
) : EntityModel{
    @PrimaryKey(autoGenerate = true) var _dbId: Long = 0L
}
