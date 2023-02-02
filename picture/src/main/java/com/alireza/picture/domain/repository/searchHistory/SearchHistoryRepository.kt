package com.alireza.picture.domain.repository.searchHistory

import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    fun searchHistory():Flow<List<SearchHistoryEntity>>
    fun removeHistory(searchHistoryEntity: SearchHistoryEntity)
    fun clearAllHistory()
}