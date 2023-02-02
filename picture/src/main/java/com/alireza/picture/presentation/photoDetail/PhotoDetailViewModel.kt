package com.alireza.picture.presentation.photoDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.domain.useCase.photoDetail.PhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val photoDetailUseCase: PhotoDetailUseCase,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val photoId: String
    private val imageUrl: String

    private val _photoState: MutableStateFlow<PhotoDetailState> = MutableStateFlow(Loading)
    val photoState: StateFlow<PhotoDetailState> = _photoState

    init {
        photoId = stateHandle.get<String>("photoId").toString()
        imageUrl = stateHandle.get<String>("url").toString()
        loadPhotoDetail()
    }

    private fun loadPhotoDetail() {
        viewModelScope.launch {
            val param = PhotoDetailParam(photoId)
                photoDetailUseCase.invoke(param)
                .flowOn(Dispatchers.IO)
                .map { useCaseModel ->
                    when (useCaseModel) {
                        is UseCaseModel.Success -> PhotoState(useCaseModel.data.apply { url = imageUrl })
                        is UseCaseModel.Error -> ErrorState(useCaseModel.code, useCaseModel.message)
                        is UseCaseModel.Exception -> ExceptionState(useCaseModel.exception)
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = Loading
                ).collect { data ->
                    when (data) {
                        is PhotoState -> _photoState.value = data
                        is ErrorState -> _errorState.tryEmit(data)
                        is ExceptionState -> _errorState.tryEmit(data)
                    }
                }
        }
    }


}