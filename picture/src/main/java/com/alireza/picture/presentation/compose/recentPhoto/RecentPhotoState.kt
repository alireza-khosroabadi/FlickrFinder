package com.alireza.picture.presentation.compose.recentPhoto

import com.alireza.core.presentation.viewModel.BaseViewModelState
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto

sealed class RecentPhotoState:BaseViewModelState

object Loading : RecentPhotoState()
data class RecentPhotoList(val photoList: List<RecentPhoto>) : RecentPhotoState()