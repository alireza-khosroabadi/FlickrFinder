package com.alireza.picture.data.repository.favoritePhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam
import com.alireza.picture.data.param.favoritePhoto.toPhotoDetailEntity
import com.alireza.picture.domain.repository.favoritePhoto.FavoritePhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FavoritePhotoRepositoryImplTest {

    private val favoritePhotoDao = mock<FavoritePhotoDao>()
    private val favoritePhotoRepository: FavoritePhotoRepository by lazy {
        FavoritePhotoRepositoryImpl(
            favoritePhotoDao
        )
    }


    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun `insert favorite photo and call dao insert method`() = runTest {
        val param = FavoritePhotoParam("", "", "", "", "", "", 0, 0, "", false)
        favoritePhotoRepository.favoritePhoto(param)
        verify(favoritePhotoDao).insert(param.toPhotoDetailEntity())
    }

    @Test
    fun `delete favorite photo and call dao delete method`() = runTest {
        val param = FavoritePhotoParam("", "", "", "", "", "", 0, 0, "", true)
        favoritePhotoRepository.favoritePhoto(param)
        verify(favoritePhotoDao).delete(param.toPhotoDetailEntity())
    }

}