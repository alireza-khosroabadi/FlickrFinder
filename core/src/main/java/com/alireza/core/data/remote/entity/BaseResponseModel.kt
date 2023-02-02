package com.alireza.core.data.remote.entity

interface BaseResponseModel: ResponseModel {
    val state: String
    val code: Int
    val message: String
}
