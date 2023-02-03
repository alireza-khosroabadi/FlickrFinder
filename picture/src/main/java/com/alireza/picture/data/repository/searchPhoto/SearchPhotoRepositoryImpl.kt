package com.alireza.picture.data.repository.searchPhoto

import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.Success
import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.remote.entity.searchPhoto.PhotoResponse
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchPhotoRepositoryImpl @Inject constructor(
    private val internetConnection: NetworkConnectivity,
    private val apiService: PictureApiService,
    private val searchHistoryDao: SearchHistoryDao
) :
    SearchPhotoRepository {
    override fun searchPhoto(searchPhotoParam: SearchPhotoParam): Flow<DataModel<List<PhotoResponse>>> {
        if (internetConnection.isInternetOn().not())
            throw InternetConnectionException()
        searchHistoryDao.insert(SearchHistoryEntity(searchPhotoParam.query))
        val result = flow { emit(apiService.searchPhoto(searchPhotoParam.query)) }.map { response ->
            if (response.state == "ok")
                Success(response.photos.photo)
            else
                ErrorModel(null, response.code, response.message)
        }
        return result
    }
}