package com.alireza.picture.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alireza.core.data.local.converter.DateConverter
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.dao.updateTable.UpdateTableDao
import com.alireza.picture.data.local.entity.photoDetail.PhotoDetailEntity
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.data.local.entity.updateTable.UpdateTableEntity

@Database(
    entities = [RecentPhotoEntity::class, SearchHistoryEntity::class, PhotoDetailEntity::class, UpdateTableEntity::class],
    version = 4
)
@TypeConverters(DateConverter::class)
abstract class PhotoDataBase : RoomDatabase() {

    abstract fun recentPhotoDao(): RecentPhotoDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun favoritePhotoDao(): FavoritePhotoDao
    abstract fun updateTableDao(): UpdateTableDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDataBase? = null

        fun getInstance(context: Context): PhotoDataBase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        ).also {
                            INSTANCE = it
                        }
                }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PhotoDataBase::class.java, "photo-db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}