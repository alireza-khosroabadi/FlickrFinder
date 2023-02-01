package com.alireza.picture.domain.useCase.searchHistory

import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.domain.usecase.FlowUseCase
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.model.searchHistory.SearchHistoryMapper
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchHistoryListUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val mapper: SearchHistoryMapper
) :
    FlowUseCase<Unit, List<SearchHistory>>() {
    override fun execute(parameters: Unit): Flow<UseCaseModel<List<SearchHistory>>> =
        searchHistoryRepository.searchHistory()
            .map { data -> UseCaseModel.Success(data.map { entity -> mapper.toDomainModel(entity) }) }
}