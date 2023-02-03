package com.alireza.picture.data.repository.photoDetail

import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.Success
import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.remote.entity.photoDetail.PhotoResponse
import com.alireza.picture.domain.repository.photoDetail.PhotoDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoDetailRepositoryImpl @Inject constructor(
    private val internetConnection: NetworkConnectivity,
    private val apiService: PictureApiService
) :
    PhotoDetailRepository {
    override fun photoDetail(param: PhotoDetailParam): Flow<DataModel<PhotoResponse>> {
        if (internetConnection.isInternetOn().not())
            throw InternetConnectionException()
        val result = flow { emit(apiService.photoDetail(param.photoId)) }.map { response ->
            if (response.state == "ok")
                Success(response.photo)
            else
                ErrorModel(null, response.code, response.message)
        }
        return result
    }
}