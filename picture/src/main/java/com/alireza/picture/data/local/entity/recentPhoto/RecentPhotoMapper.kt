package com.alireza.picture.data.local.entity.recentPhoto

import com.alireza.core.base.data.local.entity.EntityMapper
import com.alireza.picture.data.remote.entity.photo.PhotoResponse

class RecentPhotoMapper : EntityMapper<RecentPhotoEntity, PhotoResponse> {
    override fun toEntityModel(dataModel: PhotoResponse): RecentPhotoEntity = with(dataModel) {
        RecentPhotoEntity(
            id = id,
            owner = owner,
            secret = secret,
            server = server,
            farm = farm,
            title = title,
            isPublic = isPublic>0,
            isFriend = isFriend>0,
            isFamily = isFamily>0,
            url = urlS,
            height = heightS.toInt(),
            width = widthS.toInt()
        )
    }
}