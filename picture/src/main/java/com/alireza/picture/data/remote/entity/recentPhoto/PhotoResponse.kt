package com.alireza.picture.data.remote.entity.recentPhoto

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id"       ) val id       : String,
    @SerializedName("owner"    ) val owner    : String,
    @SerializedName("ownername") val ownerName: String,
    @SerializedName("secret"   ) val secret   : String,
    @SerializedName("server"   ) val server   : String,
    @SerializedName("farm"     ) val farm     : Int,
    @SerializedName("title"    ) val title    : String,
    @SerializedName("ispublic" ) val isPublic : Int,
    @SerializedName("isfriend" ) val isFriend : Int,
    @SerializedName("isfamily" ) val isFamily : Int,
    @SerializedName("url_s"    ) val urlS     : String?,
    @SerializedName("url_l"    ) val urlL     : String?,
    @SerializedName("height_s" ) val heightS  : String?,
    @SerializedName("width_s"  ) val widthS   : String?

): ResponseModel