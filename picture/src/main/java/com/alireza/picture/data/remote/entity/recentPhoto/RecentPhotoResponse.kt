package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.data.remote.entity.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class RecentPhotoResponse(
    @SerializedName("photos" ) var photos : PagePhotoResponse = PagePhotoResponse(),
    @SerializedName("stat"   ) override var state   : String,
    @SerializedName("code"   ) override var code   : Int,
    @SerializedName("message") override var message   : String

): BaseResponseModel
