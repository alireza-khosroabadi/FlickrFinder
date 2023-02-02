package com.alireza.picture.domain.model.searchPhoto

import com.alireza.core.domain.model.DomainModel

data class SearchPhoto(
    val id: String = "",
    val owner: String = "",
    val ownerName: String = "",
    val title: String = "",
    val isPublic: Boolean = false,
    val isFriend: Boolean = false,
    val isFamily: Boolean = false,
    val url: String = "",
    val height: Int = 0,
    val width: Int = 0,
    val isFavorite: Boolean = false
) : DomainModel
