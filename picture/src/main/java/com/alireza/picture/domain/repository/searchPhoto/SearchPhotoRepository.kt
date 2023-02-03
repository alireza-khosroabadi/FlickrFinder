package com.alireza.picture.domain.repository.searchPhoto

import com.alireza.core.data.repository.DataModel
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.data.remote.entity.searchPhoto.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface SearchPhotoRepository {
    fun searchPhoto(searchPhotoParam: SearchPhotoParam): Flow<DataModel<List<PhotoResponse>>>
}