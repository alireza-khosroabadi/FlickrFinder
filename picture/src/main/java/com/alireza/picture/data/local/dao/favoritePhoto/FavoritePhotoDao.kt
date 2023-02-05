package com.alireza.picture.data.local.dao.favoritePhoto

import androidx.room.Dao
import androidx.room.Query
import com.alireza.core.data.local.dao.BaseDao
import com.alireza.picture.data.local.entity.favoritePhoto.FavoritePhotoEntity
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoritePhotoDao : BaseDao<PhotoDetailEntity> {

    @Query("SELECT * FROM FAVORITE_PHOTO_TABLE")
    abstract fun favoritePhotoList(): Flow<List<PhotoDetailEntity>>

    @Query("SELECT id FROM FAVORITE_PHOTO_TABLE")
    abstract fun favoritePhotoIdList(): Flow<List<FavoritePhotoEntity>>

    @Query("DELETE FROM FAVORITE_PHOTO_TABLE WHERE id=:id")
    abstract fun removeFavoritePhotoWithId(id: String)
}