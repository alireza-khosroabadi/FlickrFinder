package com.alireza.picture.data.local.dao.searchHistory

import androidx.room.Dao
import androidx.room.Query
import com.alireza.core.data.local.dao.BaseDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchHistoryDao:BaseDao<SearchHistoryEntity> {

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE ORDER BY _id DESC")
    abstract fun recentSearch(): Flow<List<SearchHistoryEntity>>
}