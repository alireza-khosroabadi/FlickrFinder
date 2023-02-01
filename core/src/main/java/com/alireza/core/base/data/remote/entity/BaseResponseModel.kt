package com.alireza.core.base.data.remote.entity

abstract class BaseResponseModel(
    open val stat: String,
    open val code: Int,
    open val message: String
)