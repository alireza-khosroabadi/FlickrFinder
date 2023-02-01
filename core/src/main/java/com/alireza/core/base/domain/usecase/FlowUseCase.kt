package com.alireza.core.base.domain.usecase

import com.alireza.core.base.domain.model.UseCaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


/**
 * FlowUseCase is an abstract class for UseCases to simplicities and catch exception in one place
 * R is return type
 * @param P is parameter type need to return data
 * @param onFailure call when it's not null and we want to do something when it fail
 * */
abstract class FlowUseCase<in P, RETURN_TYPE> {
    operator fun invoke(
        parameters: P,
        onFailure: ((exception: Throwable) -> Unit)? = null
    ): Flow<UseCaseModel<RETURN_TYPE>> = execute(parameters)

        .catch { e ->
            onFailure?.invoke(e)
            emit(UseCaseModel.Exception(Exception(e)))
        }

    protected abstract fun execute(
        parameters: P,
        onFailure: (() -> Unit)? = null
    ): Flow<UseCaseModel<RETURN_TYPE>>
}
