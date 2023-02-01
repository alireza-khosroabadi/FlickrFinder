package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.base.data.remote.entity.BaseResponseModel
import com.alireza.core.base.data.remote.entity.ResponseModel
import com.alireza.picture.data.remote.entity.pagePhoto.PagePhotoResponse
import com.google.gson.annotations.SerializedName

data class RecentPhotoResponse(
    @SerializedName("photos" ) var photos : PagePhotoResponse? = PagePhotoResponse(),
    @SerializedName("stat"   ) override var stat   : String,
    @SerializedName("code"   ) override var code   : Int,
    @SerializedName("message"   ) override var message   : String

):BaseResponseModel(stat,code, message),ResponseModel