package com.alireza.picture.data.remote.entity.searchPhoto

import com.alireza.core.data.remote.entity.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class SearchPhotoResponse(
    @SerializedName("photos" ) var photos : PagePhotoResponse = PagePhotoResponse(),
    @SerializedName("stat"   ) override var state   : String,
    @SerializedName("code"   ) override var code   : Int,
    @SerializedName("message") override var message   : String

): BaseResponseModel
