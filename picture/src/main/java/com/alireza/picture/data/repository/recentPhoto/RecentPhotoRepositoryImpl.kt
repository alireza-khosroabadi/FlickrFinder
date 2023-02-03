package com.alireza.picture.data.repository.recentPhoto

import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.offlinePattern.networkBoundResource
import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntityMapper
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentPhotoRepositoryImpl @Inject constructor(
    private val internetConnection: NetworkConnectivity,
    private val pictureApiService: PictureApiService,
    private val recentPhotoDao: RecentPhotoDao,
    private val recentPhotoMapper: RecentPhotoEntityMapper
) :
    RecentPhotoRepository {
    override fun recentPhoto(): Flow<DataModel<List<RecentPhotoEntity>>> {
        if (internetConnection.isInternetOn().not())
            throw InternetConnectionException()
        return networkBoundResource(
            query = { recentPhotoDao.recentPhotos() },
            fetch = { pictureApiService.recentPhoto() },
            saveFetchResult = { requestType ->
                recentPhotoDao.insert(
                    requestType.photos.photo.map { data -> recentPhotoMapper.toEntityModel(data) }
                )
            }
        ) { true }
    }
}