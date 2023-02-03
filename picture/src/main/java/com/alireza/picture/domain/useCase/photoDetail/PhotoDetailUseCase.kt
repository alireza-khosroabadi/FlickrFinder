package com.alireza.picture.domain.useCase.photoDetail

import com.alireza.core.data.error.getAppError
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import com.alireza.picture.domain.model.photoDetail.PhotoDetailMapper
import com.alireza.picture.domain.repository.photoDetail.PhotoDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoDetailUseCase @Inject constructor(
    private val photoDetailRepository: PhotoDetailRepository,
    private val mapper: PhotoDetailMapper
) : FlowUseCase<PhotoDetailParam, PhotoDetail>() {
    override fun execute(parameters: PhotoDetailParam): Flow<UseCaseModel<PhotoDetail>> {
        return photoDetailRepository.photoDetail(parameters)
            .map {
                when (it) {
                    is ErrorModel -> UseCaseModel.Error(
                        it.data?.let { mapper.toDomainModel(it) },
                        it.code,
                        it.message ?: ""
                    )
                    is ExceptionModel -> UseCaseModel.Exception(it.e.getAppError())
                    is Success -> UseCaseModel.Success(mapper.toDomainModel(it.data))
                }
            }
    }
}