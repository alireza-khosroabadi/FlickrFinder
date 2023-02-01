package com.alireza.picture.data.local.entity.recentPhoto

import com.alireza.core.data.local.entity.EntityMapper
import com.alireza.picture.data.remote.entity.recentPhoto.PhotoResponse

class RecentPhotoEntityMapper : EntityMapper<RecentPhotoEntity, PhotoResponse> {
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
            url = urlS?:"https:/live.staticflickr.com/$server/${id}_${secret}_m.jpg",
            height = heightS?.toInt()?:0,
            width = widthS?.toInt()?:0
        )
    }
}