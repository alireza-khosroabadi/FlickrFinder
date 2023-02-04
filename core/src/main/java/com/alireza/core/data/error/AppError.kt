package com.alireza.core.data.error

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alireza.core.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * AppError is an idea about handle error in whole app, we can improve it to support wide range of exceptions and errors.
 * for this code its only support a few types of exceptions.
 * */

sealed class AppError(@DrawableRes val icon: Int, @StringRes val title: Int) {
    class NetworkConnection :
        AppError(icon = R.drawable.ic_no_internet, title = R.string.core_no_internet)

    class SocketTimeOut :
        AppError(icon = R.drawable.ic_server_unrichable, title = R.string.core_socket_timeout)

    class UnknownError :
        AppError(icon = R.drawable.ic_error, title = R.string.core_unknown_exception)

    class UnknownHostException :
        AppError(icon = R.drawable.ic_server_unrichable, title = R.string.core_unknown_exception)
}


fun Throwable.getAppError(): AppError {
    return when (this) {
        is InternetConnectionException -> AppError.NetworkConnection()
        is SocketTimeoutException -> AppError.SocketTimeOut()
        is UnknownHostException -> AppError.UnknownHostException()
        else -> AppError.UnknownError()
    }
}