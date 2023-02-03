package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

class PhotoCommentsCountsResponse(
    @SerializedName("_content" ) val count : Int?
): ResponseModel