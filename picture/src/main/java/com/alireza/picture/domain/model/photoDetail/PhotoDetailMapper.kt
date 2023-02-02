package com.alireza.picture.domain.model.photoDetail

import com.alireza.core.domain.model.ResponseModelMapper
import com.alireza.picture.data.remote.entity.photoDetail.PhotoResponse

class PhotoDetailMapper : ResponseModelMapper<PhotoDetail, PhotoResponse> {
    override fun toDomainModel(dataModel: PhotoResponse): PhotoDetail = with(dataModel) {
        PhotoDetail(
            id = id,
            isFavorite = isFavorite,
            ownerName = owner.realName,
            location = owner.location,
            title = title.title,
            description = description.description,
            postedDate = dates.taken,
            views = views,
            commentsCount = comments.count
        )
    }
}