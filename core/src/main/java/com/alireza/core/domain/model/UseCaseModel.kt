package com.alireza.core.domain.model

import com.alireza.core.data.error.AppError


/**
 * UseCaseModel is wrapper to hold data and exceptions and pass to presentation layer (ViewModels)
 * R is a Generic model to hold data model requested
 * */
sealed class UseCaseModel<out T> {

    data class Success<out T>(val data: T) : UseCaseModel<T>()
    data class Error<out T>(val data: T?,  val code:Int, val message:String) : UseCaseModel<T>()
    data class Exception(val error: AppError) : UseCaseModel<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Error<T> -> "Error[code=$code, message=$message]"
            is Exception -> "Error[exception=$error]"
        }
    }
}