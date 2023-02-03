package com.alireza.picture.domain.model.photoDetail

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity

class PhotoDetailEntityMapper : DomainModelMapper<PhotoDetail, PhotoDetailEntity> {
    override fun toDomainModel(dataModel: PhotoDetailEntity): PhotoDetail = with(dataModel) {
        PhotoDetail(
            id = id,
            isFavorite = true,
            ownerName = ownerName,
            location = location,
            title = title,
            description = description,
            postedDate = postedDate,
            views = views,
            commentsCount = commentsCount
        )
    }
}