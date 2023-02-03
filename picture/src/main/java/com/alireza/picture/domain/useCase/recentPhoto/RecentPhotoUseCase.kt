package com.alireza.picture.domain.useCase.recentPhoto

import com.alireza.core.data.error.getAppError
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import com.alireza.picture.domain.model.recentPhoto.RecentPhotoMapper
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentPhotoUseCase @Inject constructor(
    private val recentPhotoRepository: RecentPhotoRepository,
    private val recentPhotoMapper: RecentPhotoMapper
) : FlowUseCase<ShouldFetchParam, List<RecentPhoto>>() {
    override fun execute(
        parameters: ShouldFetchParam
    ): Flow<UseCaseModel<List<RecentPhoto>>> {
        return recentPhotoRepository.recentPhoto(parameters)
            .map {
                when (it) {
                    is ErrorModel -> UseCaseModel.Error(
                        it.data?.map { recentPhotoMapper.toDomainModel(it) },
                        it.code,
                        it.message ?: ""
                    )
                    is ExceptionModel -> UseCaseModel.Exception(it.e.getAppError())
                    is Success -> {
                        val mappedData = it.data.map { data ->
                            recentPhotoMapper.toDomainModel(
                                data
                            )
                        }
                        UseCaseModel.Success(mappedData)
                    }
                }
            }
    }
}