package com.alireza.picture.presentation.favoritePhoto.remove

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.alireza.core.presentation.viewModel.BaseViewModel
import com.alireza.picture.data.param.favoritePhoto.RemoveFavoritePhotoParam
import com.alireza.picture.domain.useCase.favoritePhoto.FavoritePhotoRemoveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoveFavoritePhotoViewModel @Inject constructor(
    private val favoritePhotoRemoveUseCase: FavoritePhotoRemoveUseCase,
    private val stateHandle: SavedStateHandle
) : BaseViewModel() {
    private val photoId: String = stateHandle.get<String>("photoId").toString()

    fun removePhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritePhotoRemoveUseCase(RemoveFavoritePhotoParam(photoId))
        }
    }

}