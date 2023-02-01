package com.alireza.picture.domain.repository.model.recentPhoto

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity

class RecentPhotoMapper : DomainModelMapper<RecentPhoto, RecentPhotoEntity> {
    override fun toDomainModel(dataModel: RecentPhotoEntity): RecentPhoto = with(dataModel) {
        RecentPhoto(
            id = id,
            owner = owner,
            title = title,
            isPublic = isPublic,
            isFavorite = isFriend,
            isFamily = isFamily,
            url = url,
            height = height,
            width = width
        )
    }
}