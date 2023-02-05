package com.alireza.picture.presentation.fakeData

import com.alireza.core.data.error.AppError
import com.alireza.core.domain.model.UseCaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeUseCaseExceptionModel: Flow<UseCaseModel.Exception> =
    flowOf(UseCaseModel.Exception(AppError.NetworkConnection()))
