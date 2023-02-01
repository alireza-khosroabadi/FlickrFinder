package com.alireza.picture.data.fakeData

import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import kotlinx.coroutines.flow.flowOf

val fakeRecentPhotoEntity = RecentPhotoEntity(
    id = "1",
    owner = "Alireza",
    secret = "XXXX",
    server = "number one",
    farm = 1,
    title = "test",
    isPublic = true,
    isFriend = false,
    isFamily = true,
    url = "",
    height = 0,
    width = 0
)
val fakeRecentPhotoEntityList = listOf(fakeRecentPhotoEntity)
val fakeRecentPhotoEntityListFlow = flowOf(fakeRecentPhotoEntityList)
