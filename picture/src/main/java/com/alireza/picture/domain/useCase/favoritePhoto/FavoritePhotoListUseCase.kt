package com.alireza.picture.domain.useCase.favoritePhoto

import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import com.alireza.picture.domain.model.photoDetail.PhotoDetailEntityMapper
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritePhotoListUseCase @Inject constructor(
    private val favoritePhotoRepository: FavoritePhotoRepository,
    private val mapper: PhotoDetailEntityMapper
) :
    FlowUseCase<Unit, List<PhotoDetail>>() {
    override fun execute(parameters: Unit): Flow<UseCaseModel<List<PhotoDetail>>> {
        return favoritePhotoRepository.favoritePhotoList()
            .map { data ->
                UseCaseModel.Success((data as Success).data.map { photo ->
                    mapper.toDomainModel(
                        photo
                    ).apply { url = photo.url }
                })
            }
    }
}