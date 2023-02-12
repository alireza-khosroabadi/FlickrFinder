package com.alireza.core.presentation.viewModel

import com.alireza.core.data.error.AppError


interface AppUiState
interface BaseViewModelState
object Initialize: BaseViewModelState
data class ErrorState(val code:Int , val message: String) : BaseViewModelState
data class ExceptionState(val error: AppError) : BaseViewModelState
