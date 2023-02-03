package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoTitleResponse(
    @SerializedName("_content" ) val title : String?
): ResponseModel
