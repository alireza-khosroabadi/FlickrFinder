package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoOwnerResponse(
    @SerializedName("realname"   ) val realName  : String?,
    @SerializedName("location"   ) val location  : String?,
    @SerializedName("path_alias" ) val pathAlias : String?
): ResponseModel
