package com.alireza.core.domain.usecase


/**
 * FlowUseCase is an abstract class for UseCases to simplicities, we should UseCase to functions don't return any result.
 * @param P is parameter type
 * @param onSuccess call when it's not null and we want to do something when it success
 * @param onException call when it's not null and we want to do something when it fail
 * */
abstract class UseCase<in P> {

    suspend operator fun invoke(
        parameters: P,
        onSuccess: (() -> Unit)? = null,
        onException: ((exception: Throwable) -> Unit)? = null
    ) {
        try {
            execute(parameters)
            onSuccess?.invoke()
        } catch (e: Exception) {
            onException?.invoke(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(
        parameters: P,
        onSuccess: (() -> Unit)? = null,
        onException: (() -> Unit)? = null
    )
}