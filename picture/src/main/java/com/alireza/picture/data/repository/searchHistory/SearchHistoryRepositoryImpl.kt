package com.alireza.picture.data.repository.searchHistory

import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(private val searchHistoryDao: SearchHistoryDao) :
    SearchHistoryRepository {
    override fun searchHistory(): Flow<List<SearchHistoryEntity>> =
        searchHistoryDao.recentSearch()
}