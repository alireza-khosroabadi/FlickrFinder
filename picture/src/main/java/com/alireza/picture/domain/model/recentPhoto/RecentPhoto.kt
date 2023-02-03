package com.alireza.picture.domain.model.recentPhoto

import com.alireza.core.domain.model.DomainModel

data class RecentPhoto(
    val id: String = "",
    val owner: String = "",
    val ownerName: String = "",
    val title: String = "",
    val isPublic: Boolean = false,
    val isFriend: Boolean = false,
    val isFamily: Boolean = false,
    val url: String = "",
    val urlLarge: String = "",
    val height: Int = 0,
    val width: Int = 0,
    val isFavorite: Boolean = false
) : DomainModel
