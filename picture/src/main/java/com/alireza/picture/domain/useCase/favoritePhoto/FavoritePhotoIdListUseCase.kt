package com.alireza.picture.domain.useCase.favoritePhoto

import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.domain.model.favoritePhoto.FavoritePhoto
import com.alireza.picture.domain.model.favoritePhoto.FavoritePhotoMapper
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritePhotoIdListUseCase @Inject constructor(
    private val favoritePhotoRepository: FavoritePhotoRepository,
    private val mapper: FavoritePhotoMapper
) :
    FlowUseCase<Unit, List<FavoritePhoto>>() {
    override fun execute(parameters: Unit): Flow<UseCaseModel<List<FavoritePhoto>>> {
        return favoritePhotoRepository.favoritePhotoIdList()
            .map { data ->
                UseCaseModel.Success((data as Success).data.map { photo ->
                    mapper.toDomainModel(
                        photo
                    )
                })
            }
    }
}