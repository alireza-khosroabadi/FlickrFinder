package com.alireza.picture.domain.repository.useCase.recentPhoto

import com.alireza.core.base.data.repository.ErrorModel
import com.alireza.core.base.data.repository.ExceptionModel
import com.alireza.core.base.data.repository.Success
import com.alireza.core.base.domain.model.UseCaseModel
import com.alireza.core.base.domain.usecase.FlowUseCase
import com.alireza.picture.domain.repository.model.recentPhoto.RecentPhoto
import com.alireza.picture.domain.repository.model.recentPhoto.RecentPhotoMapper
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentPhotoUseCase @Inject constructor(
    private val recentPhotoRepository: RecentPhotoRepository,
    private val recentPhotoMapper: RecentPhotoMapper
) :
    FlowUseCase<Unit, List<RecentPhoto>>() {
    override fun execute(
        parameters: Unit,
        onFailure: (() -> Unit)?
    ): Flow<UseCaseModel<List<RecentPhoto>>> {
        return recentPhotoRepository.recentPhoto()
            .map {
                when (it) {
                    is ErrorModel -> UseCaseModel.Error(it.data, it.code, it.message ?: "")
                    is ExceptionModel -> UseCaseModel.Exception(it.e)
                    is Success -> UseCaseModel.Success(it.data.map { data ->
                        recentPhotoMapper.toDomainModel(
                            data
                        )
                    })
                }
            }
    }
}