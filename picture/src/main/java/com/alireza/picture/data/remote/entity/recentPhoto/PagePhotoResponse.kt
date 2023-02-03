package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.data.remote.entity.ResponseModel
import com.alireza.picture.data.remote.entity.recentPhoto.PhotoResponse
import com.google.gson.annotations.SerializedName

data class PagePhotoResponse(
    @SerializedName("page"    ) val page    : Int?             = null,
    @SerializedName("pages"   ) val pages   : String?          = null,
    @SerializedName("perpage" ) val perPage : Int?             = null,
    @SerializedName("total"   ) val total   : String?          = null,
    @SerializedName("photo"   ) val photo   : List<PhotoResponse> = listOf()
): ResponseModel
