package com.alireza.picture.domain.repository.recentPhoto

import com.alireza.core.base.data.repository.DataModel
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import kotlinx.coroutines.flow.Flow

interface RecentPhotoRepository {
    fun recentPhoto(): Flow<DataModel<List<RecentPhotoEntity>>>
}