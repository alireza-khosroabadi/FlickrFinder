package com.alireza.picture.data.local.entity.recentPhoto

import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityResponse
import org.junit.Assert.*
import org.junit.Test


class RecentPhotoMapperTest {
    private val mapper: RecentPhotoEntityMapper by lazy { RecentPhotoEntityMapper() }

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
            "52663009941" ,
            fakeRecentPhotoEntityResponse.photos.photo.map { data -> mapper.toEntityModel(data) }[0].id
        )
    }

}