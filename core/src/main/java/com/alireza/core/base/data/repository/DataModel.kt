package com.alireza.core.base.data.repository

sealed class DataModel<T: Any>

    class Success<T: Any>(val data: T) : DataModel<T>()
    class ErrorModel<T: Any>(val data: T?, val code: Int, val message: String?) : DataModel<T>()
    class ExceptionModel<T: Any>(val e: Throwable) : DataModel<T>()