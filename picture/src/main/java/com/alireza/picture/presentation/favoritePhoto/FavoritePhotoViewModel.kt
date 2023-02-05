package com.alireza.picture.presentation.favoritePhoto

import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.domain.useCase.favoritePhoto.FavoritePhotoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePhotoViewModel @Inject constructor(private val favoritePhotoListUseCase: FavoritePhotoListUseCase) :
    BaseViewModel() {

    private val _favoritePhotoState = MutableStateFlow<FavoritePhotoState>(Loading)
    val favoritePhotoState: StateFlow<FavoritePhotoState> = _favoritePhotoState

    init {
        loadFavoritePhoto()
    }

    fun loadFavoritePhoto() {
        viewModelScope.launch {
            favoritePhotoListUseCase.invoke(Unit)
                .flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> FavoritePhotoList(useCaseModel.data)
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
                        is FavoritePhotoList -> _favoritePhotoState.value = data
                        is ErrorState -> _errorState.tryEmit(data)
                        is ExceptionState -> _errorState.tryEmit(data)
                    }
                }
        }
    }

}