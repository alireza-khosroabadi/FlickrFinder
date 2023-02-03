package com.alireza.picture.data.remote.api

import com.alireza.picture.data.remote.entity.photoDetail.PhotoDetailResponse
import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse
import com.alireza.picture.data.remote.entity.searchPhoto.SearchPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApiService {

    @GET(RECENT_PHOTO_URL)
    suspend fun recentPhoto(): RecentPhotoResponse

    @GET(SEARCH_PHOTO_URL)
    suspend fun searchPhoto(@Query("text") text: String): SearchPhotoResponse

    @GET(PHOTO_DETAILS_URL)
    suspend fun photoDetail(@Query("photo_id") text: String): PhotoDetailResponse

}