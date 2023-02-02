package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.data.remote.entity.ResponseModel
import com.alireza.picture.data.remote.entity.recentPhoto.PhotoResponse
import com.google.gson.annotations.SerializedName

data class PagePhotoResponse(
    @SerializedName("page"    ) var page    : Int?             = null,
    @SerializedName("pages"   ) var pages   : String?          = null,
    @SerializedName("perpage" ) var perPage : Int?             = null,
    @SerializedName("total"   ) var total   : String?          = null,
    @SerializedName("photo"   ) var photo   : List<PhotoResponse> = listOf()
): ResponseModel
