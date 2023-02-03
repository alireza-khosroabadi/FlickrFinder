package com.alireza.core.domain.usecase

import com.alireza.core.data.error.getAppError
import com.alireza.core.domain.model.UseCaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


/**
 * FlowUseCase is an abstract class for UseCases to simplicities and catch exception in one place
 * R is return type
 * @param P is parameter type need to return data
 * */
abstract class FlowUseCase<in P, RETURN_TYPE> {
    operator fun invoke(
        parameters: P
    ): Flow<UseCaseModel<RETURN_TYPE>> = try {
        execute(parameters)
            .catch { e ->
                e.printStackTrace()
                emit(UseCaseModel.Exception(e.getAppError()))
            }
    } catch (e: Exception) {
        flow { emit(UseCaseModel.Exception(e.getAppError())) }
    }

    protected abstract fun execute(parameters: P): Flow<UseCaseModel<RETURN_TYPE>>
}
