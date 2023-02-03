package com.alireza.picture.presentation.recentPhoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.domain.useCase.recentPhoto.RecentPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentPhotoViewModel @Inject constructor(private val getRecentPhoto: RecentPhotoUseCase) :
    ViewModel() {

    private val _recentPhotoState = MutableStateFlow<RecentPhotoState>(Loading)
    val recentPhotoState: StateFlow<RecentPhotoState> = _recentPhotoState


    init {
        loadRecentPhoto()
    }

    fun loadRecentPhoto() {
        viewModelScope.launch {
            getRecentPhoto.invoke(Unit).flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> RecentPhotoList(useCaseModel.data)
                        is UseCaseModel.Error -> Error(useCaseModel.code, useCaseModel.message)
                        is UseCaseModel.Exception -> Exception(useCaseModel.error)
                    }
                }
                .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Loading
            ).collect{data ->
                    _recentPhotoState.value = data
                }
        }
    }

}