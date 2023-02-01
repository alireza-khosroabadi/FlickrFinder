package com.alireza.picture.data.repository.recentPhoto

import com.alireza.core.base.data.repository.DataModel
import com.alireza.core.base.data.repository.offlinePattern.networkBoundResource
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoMapper
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentPhotoRepositoryImpl @Inject constructor(
    private val pictureApiService: PictureApiService,
    private val recentPhotoDao: RecentPhotoDao,
    private val recentPhotoMapper: RecentPhotoMapper
) :
    RecentPhotoRepository {
    override fun recentPhoto(): Flow<DataModel<List<RecentPhotoEntity>>> = networkBoundResource(
        query = { recentPhotoDao.recentPhotos() },
        fetch = { pictureApiService.recentPhoto() },
        saveFetchResult = { requestType ->
            recentPhotoDao.insert(
                requestType.photos.photo.map { data -> recentPhotoMapper.toEntityModel(data) }
            )
        }
    ) { true }
}