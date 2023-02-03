package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoDatesResponse(
    @SerializedName("posted" ) val posted : Long?,
    @SerializedName("taken"  ) val taken  : String?
): ResponseModel
