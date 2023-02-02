package com.alireza.picture.domain.useCase.searchHistory

import com.alireza.core.domain.usecase.UseCase
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import javax.inject.Inject

class RemoveSearchHistoryUseCase @Inject constructor(private val searchHistoryRepository: SearchHistoryRepository) :
    UseCase<SearchHistory>() {
    override suspend fun execute(
        parameters: SearchHistory,
        onSuccess: (() -> Unit)?,
        onException: (() -> Unit)?
    ) {
        searchHistoryRepository.removeHistory(SearchHistoryEntity(parameters.query))
    }
}