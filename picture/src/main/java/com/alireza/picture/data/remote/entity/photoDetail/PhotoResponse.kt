package com.alireza.picture.data.remote.entity.photoDetail

import com.alireza.core.data.local.entity.EntityModel
import com.alireza.core.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id"           ) val id           : String,
    @SerializedName("dateuploaded" ) val dateUploaded : String?,
    @SerializedName("isfavorite"   ) val isFavorite   : Int?,
    @SerializedName("owner"        ) val owner        : PhotoOwnerResponse?,
    @SerializedName("title"        ) val title        : PhotoTitleResponse?,
    @SerializedName("description"  ) val description  : PhotoDescriptionResponse?,
    @SerializedName("dates"        ) val dates        : PhotoDatesResponse?,
    @SerializedName("views"        ) val views        : Int?,
    @SerializedName("comments"     ) val comments     : PhotoCommentsCountsResponse?,
    @SerializedName("media"        ) val media        : String?
):ResponseModel
