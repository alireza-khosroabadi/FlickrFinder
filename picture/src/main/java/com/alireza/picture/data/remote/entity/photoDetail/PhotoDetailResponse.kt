package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.remote.entity.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoDetailResponse (
        @SerializedName("photo"  ) var photo : PhotoResponse,
        @SerializedName("stat"   ) override var state   : String,
        @SerializedName("code"   ) override var code   : Int,
        @SerializedName("message") override var message   : String
):BaseResponseModel