package com.alireza.picture.presentation.searchPhoto

import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto

sealed class SearchPhotoHistoryState
object Loading : SearchPhotoHistoryState()
data class SearchHistoryList(val lastHistory: List<SearchHistory>) : SearchPhotoHistoryState()

sealed class SearchPhotoState
object SearchPhotoLoading : SearchPhotoState()
data class SearchPhotoList(val photoList: List<SearchPhoto>) : SearchPhotoState()
