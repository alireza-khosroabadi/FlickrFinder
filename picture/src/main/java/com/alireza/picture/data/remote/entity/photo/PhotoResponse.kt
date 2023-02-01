package com.alireza.picture.data.remote.entity.photo

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id"       ) var id       : String,
    @SerializedName("owner"    ) var owner    : String,
    @SerializedName("secret"   ) var secret   : String,
    @SerializedName("server"   ) var server   : String,
    @SerializedName("farm"     ) var farm     : Int,
    @SerializedName("title"    ) var title    : String,
    @SerializedName("ispublic" ) var isPublic : Int,
    @SerializedName("isfriend" ) var isFriend : Int,
    @SerializedName("isfamily" ) var isFamily : Int,
    @SerializedName("url_s"    ) var urlS     : String?,
    @SerializedName("height_s" ) var heightS  : String?,
    @SerializedName("width_s"  ) var widthS   : String?

): ResponseModel