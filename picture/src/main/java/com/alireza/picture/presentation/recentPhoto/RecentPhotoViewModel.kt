package com.alireza.picture.presentation.recentPhoto

import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.domain.useCase.recentPhoto.RecentPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentPhotoViewModel @Inject constructor(private val getRecentPhoto: RecentPhotoUseCase) :
    BaseViewModel() {

    private val _recentPhotoState = MutableStateFlow<RecentPhotoState>(Loading)
    val recentPhotoState: StateFlow<RecentPhotoState> = _recentPhotoState


    init {
        loadRecentPhoto()
    }

    fun loadRecentPhoto(forceToFetch: Boolean = false) {
        viewModelScope.launch {
            getRecentPhoto.invoke(ShouldFetchParam(forceToFetch)).flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> RecentPhotoList(useCaseModel.data)
                        is UseCaseModel.Error -> ErrorState(useCaseModel.code, useCaseModel.message)
                        is UseCaseModel.Exception -> ExceptionState(useCaseModel.error)
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = Loading
                ).collect { data ->
                    when (data) {
                        is RecentPhotoList -> _recentPhotoState.value = data
                        is ErrorState -> _errorState.tryEmit(data)
                        is ExceptionState -> _errorState.tryEmit(data)
                    }
                }
        }
    }

}