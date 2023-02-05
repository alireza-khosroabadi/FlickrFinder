package com.alireza.picture.presentation.recentPhoto

import com.alireza.picture.domain.model.recentPhoto.RecentPhoto

sealed class RecentPhotoState

object Loading : RecentPhotoState()
data class RecentPhotoList(val photoList: List<RecentPhoto>) : RecentPhotoState()