package com.alireza.picture.data.remote.entity.photo

import com.alireza.core.base.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id"       ) var id       : String? = null,
    @SerializedName("owner"    ) var owner    : String? = null,
    @SerializedName("secret"   ) var secret   : String? = null,
    @SerializedName("server"   ) var server   : String? = null,
    @SerializedName("farm"     ) var farm     : Int?    = null,
    @SerializedName("title"    ) var title    : String? = null,
    @SerializedName("ispublic" ) var isPublic : Boolean?    = null,
    @SerializedName("isfriend" ) var isFriend : Boolean?    = null,
    @SerializedName("isfamily" ) var isFamily : Boolean?    = null,
    @SerializedName("url_s"    ) var urlS     : String? = null,
    @SerializedName("height_s" ) var heightS  : String? = null,
    @SerializedName("width_s"  ) var widthS   : String? = null

):ResponseModel