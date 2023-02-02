package com.alireza.picture.domain.model.recentPhoto

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity

class RecentPhotoMapper : DomainModelMapper<RecentPhoto, RecentPhotoEntity> {
    override fun toDomainModel(dataModel: RecentPhotoEntity): RecentPhoto = with(dataModel) {
        RecentPhoto(
            id = id,
            owner = owner,
            ownerName = ownerName,
            title = title,
            isPublic = isPublic,
            isFavorite = isFriend,
            isFamily = isFamily,
            url = url,
            urlLarge = urlLarge,
            height = height,
            width = width
        )
    }
}