package com.alireza.picture.presentation.searchPhoto.searchHistoryAdapter

import com.alireza.picture.domain.model.searchHistory.SearchHistory

interface SearchHistoryListener {
    fun onSearchHistoryItemClick(searchItem:SearchHistory)
    fun onRemoveHistoryClick(searchHistory: SearchHistory)
}