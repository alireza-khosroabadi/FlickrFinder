package com.alireza.picture.domain.repository.recentPhoto

import com.alireza.core.data.repository.DataModel
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import kotlinx.coroutines.flow.Flow

interface RecentPhotoRepository {
    fun recentPhoto(forceToFetch: ShouldFetchParam): Flow<DataModel<List<RecentPhotoEntity>>>
}