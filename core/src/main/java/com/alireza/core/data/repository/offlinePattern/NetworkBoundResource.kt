package com.alireza.core.data.repository.offlinePattern

import com.alireza.core.data.remote.entity.BaseResponseModel
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * inline function for implement offline first
 * @param query is lambda to fetch data from local storage or cache like database or sharedPreferences
 * @param fetch is lambda to fetch data from network api
 * @param saveFetchResult is lambda to save data in local storage
 * @param shouldFetch is lambda with boolean return to make decision for fetch data from network or not
 * */

inline fun <ResultType: Any, RequestType: BaseResponseModel> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: () -> Boolean = { true },
) = flow {
    // true -> api status== ok and false -> api status==false
    var resultStatus = true
    var apiErrorCode = 0
    var apiErrorMessage = ""
    if (shouldFetch()) {
        // Need to fetch data -> call backend
        val fetchResult = fetch()
        resultStatus = fetchResult.state=="ok"
        // got data from backend, store it in database
        if (resultStatus)
            saveFetchResult(fetchResult)
        else{
            apiErrorCode = fetchResult.code
            apiErrorMessage = fetchResult.message
        }
    }
    // load updated data from database (must not return null anymore)
    val updatedData = query().first()
    if (resultStatus) {
        // emit updated data
        emit(Success(updatedData))
    }else{
        emit(ErrorModel(updatedData,apiErrorCode,apiErrorMessage))
    }

}.catch { exception ->
    exception.printStackTrace()
    emit(ExceptionModel(exception))

}