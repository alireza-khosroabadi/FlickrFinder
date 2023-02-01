package com.alireza.core.base.data.remote.entity

interface BaseResponseModel:ResponseModel {
    val state: String
    val code: Int
    val message: String
}
