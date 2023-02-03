package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.data.remote.entity.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class RecentPhotoResponse(
    @SerializedName("photos" ) val photos : PagePhotoResponse = PagePhotoResponse(),
    @SerializedName("stat"   ) override val state   : String,
    @SerializedName("code"   ) override val code   : Int,
    @SerializedName("message") override val message   : String

): BaseResponseModel
