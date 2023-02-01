package com.alireza.picture.data.remote.api

import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse
import retrofit2.http.GET

interface PictureApiService {

    @GET(RECENT_PHOTO_URL)
    suspend fun recentPhoto():RecentPhotoResponse
}