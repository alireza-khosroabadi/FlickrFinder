package com.alireza.picture.domain.useCase.searchPhoto

import com.alireza.core.data.error.getAppError
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto
import com.alireza.picture.domain.model.searchPhoto.SearchPhotoMapper
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchPhotoUseCase @Inject constructor(
    private val searchPhotoRepository: SearchPhotoRepository,
    private val mapper: SearchPhotoMapper
) :
    FlowUseCase<SearchPhotoParam, List<SearchPhoto>>() {
    override fun execute(parameters: SearchPhotoParam): Flow<UseCaseModel<List<SearchPhoto>>> {
        return searchPhotoRepository.searchPhoto(parameters)
            .map {
                when (it) {
                    is ErrorModel -> UseCaseModel.Error(
                        it.data?.map { mapper.toDomainModel(it) },
                        it.code,
                        it.message ?: ""
                    )
                    is ExceptionModel -> UseCaseModel.Exception(it.e.getAppError())
                    is Success -> {
                        val mappedData = it.data.map { data ->
                            mapper.toDomainModel(
                                data
                            )
                        }
                        UseCaseModel.Success(mappedData)
                    }
                }
            }
    }
}