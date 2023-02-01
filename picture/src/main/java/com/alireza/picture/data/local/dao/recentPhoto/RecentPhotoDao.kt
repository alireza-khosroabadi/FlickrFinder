package com.alireza.picture.data.local.dao.recentPhoto

import androidx.room.Dao
import androidx.room.Query
import com.alireza.core.base.data.local.dao.BaseDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecentPhotoDao: BaseDao<RecentPhotoEntity> {
    @Query("SELECT * FROM RECENT_PHOTO_TABLE")
    abstract fun recentPhotos(): Flow<List<RecentPhotoEntity>>
}