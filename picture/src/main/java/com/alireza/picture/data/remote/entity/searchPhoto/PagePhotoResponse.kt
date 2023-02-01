package com.alireza.picture.data.remote.entity.searchPhoto

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PagePhotoResponse(
    @SerializedName("page"    ) var page    : Int?             = null,
    @SerializedName("pages"   ) var pages   : String?          = null,
    @SerializedName("perpage" ) var perPage : Int?             = null,
    @SerializedName("total"   ) var total   : String?          = null,
    @SerializedName("photo"   ) var photo   : List<PhotoResponse> = listOf()
): ResponseModel
