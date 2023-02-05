package com.alireza.flickrfinder.fakeData.fakeHiltModule

import com.alireza.flickrfinder.fakeData.fakeDataModel.fakeRecentPhotoEntityResponse
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.remote.entity.photoDetail.*
import com.alireza.picture.data.remote.entity.recentPhoto.PagePhotoResponse
import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse
import com.alireza.picture.data.remote.entity.searchPhoto.SearchPhotoResponse


class EmptyResponseApiService() : PictureApiService {
    override suspend fun recentPhoto(): RecentPhotoResponse =
        RecentPhotoResponse(PagePhotoResponse(), "ok", 0, "")

    override suspend fun searchPhoto(text: String): SearchPhotoResponse =
        SearchPhotoResponse(
            com.alireza.picture.data.remote.entity.searchPhoto.PagePhotoResponse(),
            "ok",
            0,
            ""
        )

    override suspend fun photoDetail(text: String): PhotoDetailResponse =
        PhotoDetailResponse(
            PhotoResponse(
                "", "", 0, PhotoOwnerResponse("", "", ""),
                PhotoTitleResponse(""), PhotoDescriptionResponse(""),
                PhotoDatesResponse(0L, ""), 0,
                PhotoCommentsCountsResponse(0), ""
            ), "ok", 0, ""
        )

}


class ResponseApiService() : PictureApiService {
    override suspend fun recentPhoto(): RecentPhotoResponse = fakeRecentPhotoEntityResponse

    override suspend fun searchPhoto(text: String): SearchPhotoResponse =
        SearchPhotoResponse(
            com.alireza.picture.data.remote.entity.searchPhoto.PagePhotoResponse(),
            "ok",
            0,
            ""
        )

    override suspend fun photoDetail(text: String): PhotoDetailResponse =
        PhotoDetailResponse(
            PhotoResponse(
                "", "", 0, PhotoOwnerResponse("", "", ""),
                PhotoTitleResponse(""), PhotoDescriptionResponse(""),
                PhotoDatesResponse(0L, ""), 0,
                PhotoCommentsCountsResponse(0), ""
            ), "ok", 0, ""
        )

}