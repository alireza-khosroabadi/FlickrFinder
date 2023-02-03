package com.alireza.picture.domain.model.searchPhoto

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.core.domain.model.ResponseModelMapper
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.remote.entity.searchPhoto.PhotoResponse

class SearchPhotoMapper : ResponseModelMapper<SearchPhoto, PhotoResponse> {
    override fun toDomainModel(dataModel: PhotoResponse): SearchPhoto = with(dataModel) {
        SearchPhoto(
            id = id,
            owner = owner,
            ownerName = owner,
            title = title,
            isPublic = isPublic>0,
            isFriend = isFriend>0,
            isFamily = isFamily>0,
            url = urlS?:"https:/live.staticflickr.com/$server/${id}_${secret}_m.jpg",
            urlLarge = urlL?:"https:/live.staticflickr.com/$server/${id}_${secret}_m.jpg",
            height = heightS?.toInt()?:0,
            width = widthS?.toInt()?:0
        )
    }
}