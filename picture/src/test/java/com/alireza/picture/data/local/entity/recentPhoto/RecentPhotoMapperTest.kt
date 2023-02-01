package com.alireza.picture.data.local.entity.recentPhoto

import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityResponse
import org.junit.Assert.*
import org.junit.Test


class RecentPhotoMapperTest {
    private val mapper: RecentPhotoMapper by lazy { RecentPhotoMapper() }

    @Test
    fun `test convert recent photo response to recent photo entity return one item`() {
        assertEquals(
            1,
            fakeRecentPhotoEntityResponse.photos.photo.map { data -> mapper.toEntityModel(data) }.size
        )
    }

    @Test
    fun `test convert recent photo response to recent photo entity return correct id`(){
        assertEquals(
            "52662561938" ,
            fakeRecentPhotoEntityResponse.photos.photo.map { data -> mapper.toEntityModel(data) }[0].id
        )
    }

}