package com.alireza.core.presentation.viewModel

sealed class BaseViewModelState
data class ErrorState(val code:Int , val message: String) : BaseViewModelState()
data class ExceptionState(val throwable: Throwable) : BaseViewModelState()
