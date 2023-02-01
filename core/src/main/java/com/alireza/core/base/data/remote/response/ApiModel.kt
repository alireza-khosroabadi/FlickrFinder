package com.alireza.core.base.data.remote.response

import com.alireza.core.base.data.remote.entity.ResponseModel
import com.google.gson.annotations.SerializedName

abstract class ApiModel<T : ResponseModel>(
    val data: T? = null,
    @SerializedName("stat")
    val stat: String = "",
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = ""
)