package com.alireza.picture.domain.repository.photoDetail

import com.alireza.core.data.repository.DataModel
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.data.remote.entity.photoDetail.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface PhotoDetailRepository {
    fun photoDetail(param:PhotoDetailParam): Flow<DataModel<PhotoResponse>>
}