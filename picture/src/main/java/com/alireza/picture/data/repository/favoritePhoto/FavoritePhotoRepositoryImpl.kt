package com.alireza.picture.data.repository.favoritePhoto

import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.Success
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.entity.favoritePhoto.FavoritePhotoEntity
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam
import com.alireza.picture.data.param.favoritePhoto.RemoveFavoritePhotoParam
import com.alireza.picture.data.param.favoritePhoto.toPhotoDetailEntity
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritePhotoRepositoryImpl @Inject constructor(private val favoritePhotoDao: FavoritePhotoDao) :
    FavoritePhotoRepository {
    override fun favoritePhotoList(): Flow<DataModel<List<PhotoDetailEntity>>> =
        favoritePhotoDao.favoritePhotoList()
            .map { favoriteList ->
                Success(favoriteList)
            }

    override fun favoritePhotoIdList(): Flow<DataModel<List<FavoritePhotoEntity>>> =
        favoritePhotoDao.favoritePhotoIdList()
            .map { favoriteList -> Success(favoriteList) }


    override fun favoritePhoto(photo: FavoritePhotoParam) {
        if (photo.isFavorite)
            favoritePhotoDao.delete(photo.toPhotoDetailEntity())
        else
            favoritePhotoDao.insert(photo.toPhotoDetailEntity())
    }

   override fun removeFavoritePhoto(param:RemoveFavoritePhotoParam){
        favoritePhotoDao.removeFavoritePhotoWithId(param.id)
    }
}