package com.alireza.picture.presentation.photoDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import com.alireza.picture.domain.model.photoDetail.toFavoritePhotoParam
import com.alireza.picture.domain.useCase.favoritePhoto.FavoritePhotoIdListUseCase
import com.alireza.picture.domain.useCase.favoritePhoto.FavoritePhotoUseCase
import com.alireza.picture.domain.useCase.photoDetail.PhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val photoDetailUseCase: PhotoDetailUseCase,
    private val favoritePhotoIdListUseCase: FavoritePhotoIdListUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val photoId: String
    private val imageUrl: String
    private lateinit var photoDetail: PhotoDetail
    private var isFavoritePhoto: Boolean = false
    private val _photoState: MutableStateFlow<PhotoDetailState> = MutableStateFlow(Loading)
    val photoState: StateFlow<PhotoDetailState> = _photoState

    init {
        photoId = stateHandle.get<String>("photoId").toString()
        imageUrl = stateHandle.get<String>("url").toString()
        loadPhotoDetail()
    }

    private fun loadPhotoDetail() {
        viewModelScope.launch {
            combine(
                photoDetailUseCase.invoke(PhotoDetailParam(photoId)),
                favoritePhotoIdListUseCase(Unit)
            ) { photoDetail, favoritePhotoIds ->
                // Combine two flow to detect photo is favorite or not
                when (photoDetail) {
                    is UseCaseModel.Error -> ErrorState(photoDetail.code, photoDetail.message)
                    is UseCaseModel.Exception -> ExceptionState(photoDetail.error)
                    else -> {
                        PhotoState((photoDetail as UseCaseModel.Success).data.apply {
                            url = imageUrl
                            isFavorite =
                                (favoritePhotoIds as UseCaseModel.Success)
                                    .data.any { favoritePhoto -> favoritePhoto.id == id }
                            this@PhotoDetailViewModel.photoDetail = this
                            this@PhotoDetailViewModel.isFavoritePhoto = isFavorite
                        })
                    }
                }
            }
                .flowOn(Dispatchers.IO)
                .stateIn(
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

    fun favoritePhoto() {
        if (::photoDetail.isInitialized) {
            viewModelScope.launch(Dispatchers.IO) {
                favoritePhotoUseCase.invoke(photoDetail.toFavoritePhotoParam())
                isFavoritePhoto = isFavoritePhoto.not()
                _photoState.value = IsFavoritePhoto(isFavoritePhoto)
            }
        }
    }


}