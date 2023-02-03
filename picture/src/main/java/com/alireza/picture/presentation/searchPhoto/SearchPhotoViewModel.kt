package com.alireza.picture.presentation.searchPhoto

import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.useCase.searchHistory.ClearSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.RemoveSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.SearchHistoryListUseCase
import com.alireza.picture.domain.useCase.searchPhoto.SearchPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPhotoViewModel @Inject constructor(
    private val searchPhotoUseCase: SearchPhotoUseCase,
    private val historyPhotoUseCase: SearchHistoryListUseCase,
    private val removeSearchHistoryUseCase: RemoveSearchHistoryUseCase,
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase
) : BaseViewModel() {

    private val _searchHistoryState = MutableStateFlow<SearchPhotoHistoryState>(Loading)
    val searchHistoryState: StateFlow<SearchPhotoHistoryState> = _searchHistoryState


    private val _searchPhotoState = MutableStateFlow<SearchPhotoState>(SearchPhotoLoading)
    val searchPhotoState: StateFlow<SearchPhotoState> = _searchPhotoState


    init {
        loadLastSearchHistory()
    }

    private fun loadLastSearchHistory() {
        viewModelScope.launch {
            historyPhotoUseCase.invoke(Unit)
                .flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> SearchHistoryList(useCaseModel.data)
                        is UseCaseModel.Error -> ErrorState(useCaseModel.code, useCaseModel.message)
                        is UseCaseModel.Exception -> ExceptionState(useCaseModel.error)
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = com.alireza.picture.presentation.recentPhoto.Loading
                ).collect { data ->
                    when (data) {
                        is SearchHistoryList -> _searchHistoryState.value = data
                        is ErrorState -> _errorState.tryEmit(data)
                        is ExceptionState -> _errorState.tryEmit(data)
                    }
                }

        }
    }

    fun searchPhoto(query: String) {
        _searchPhotoState.value = SearchPhotoLoading
        viewModelScope.launch(Dispatchers.IO) {
            searchPhotoUseCase.invoke(SearchPhotoParam(query))
                .flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> SearchPhotoList(useCaseModel.data)
                        is UseCaseModel.Error -> ErrorState(useCaseModel.code, useCaseModel.message)
                        is UseCaseModel.Exception -> ExceptionState(useCaseModel.error)
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = SearchPhotoLoading
                ).collect { data ->
                    when (data) {
                        is SearchPhotoList -> _searchPhotoState.value = data
                        is ErrorState -> _errorState.tryEmit(data)
                        is ExceptionState -> _errorState.tryEmit(data)
                    }
                }
        }
    }

    fun removeHistory(searchHistory: SearchHistory) {
        viewModelScope.launch(Dispatchers.IO) {
            removeSearchHistoryUseCase.invoke(searchHistory)
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            clearSearchHistoryUseCase.invoke(Unit)
        }
    }
}