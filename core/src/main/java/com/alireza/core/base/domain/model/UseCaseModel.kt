package com.alireza.core.base.domain.model


/**
 * UseCaseModel is wrapper to hold data and exceptions and pass to presentation layer (ViewModels)
 * R is a Generic model to hold data model requested
 * */
sealed class UseCaseModel<out R> {

    data class Success<out T>(val data: T) : UseCaseModel<T>()
    data class Error(val code:Int, val message:String) : UseCaseModel<Nothing>()
    data class Exception(val exception: Throwable) : UseCaseModel<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[code=$code, message=$message]"
            is Exception -> "Error[exception=$exception]"
        }
    }
}