package com.alireza.picture.domain.model.searchHistory

import com.alireza.core.domain.model.DomainModelMapper
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity

class SearchHistoryMapper : DomainModelMapper<SearchHistory, SearchHistoryEntity> {
    override fun toDomainModel(dataModel: SearchHistoryEntity): SearchHistory =
        with(dataModel) { SearchHistory( query) }
}