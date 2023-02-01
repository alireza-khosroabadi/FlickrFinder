package com.alireza.core.base.data.remote.entity

import com.google.gson.annotations.SerializedName

abstract class ResponseModel(
    val stat: String,
    val code: Int,
    val message: String
)