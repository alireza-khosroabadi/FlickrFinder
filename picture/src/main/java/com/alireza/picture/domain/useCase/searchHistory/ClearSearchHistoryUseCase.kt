package com.alireza.picture.domain.useCase.searchHistory

import com.alireza.core.domain.usecase.UseCase
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import javax.inject.Inject

class ClearSearchHistoryUseCase @Inject constructor(private val searchHistoryRepository: SearchHistoryRepository) :
    UseCase<Unit>() {
    override suspend fun execute(
        parameters: Unit,
        onSuccess: (() -> Unit)?,
        onException: (() -> Unit)?
    ) {
        searchHistoryRepository.clearAllHistory()
    }
}