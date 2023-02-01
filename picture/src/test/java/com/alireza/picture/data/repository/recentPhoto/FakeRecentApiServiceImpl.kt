package com.alireza.picture.data.repository.recentPhoto

import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityResponse
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse

class FakeRecentApiServiceImpl : PictureApiService {
    override suspend fun recentPhoto(): RecentPhotoResponse = fakeRecentPhotoEntityResponse
}

