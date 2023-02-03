package com.alireza.picture.data.repository.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.Success
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityListFlow
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityResponse
import com.alireza.picture.data.fakeData.fakeRecentPhotoFailedFlow
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntityMapper
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RecentPhotoRepositoryImplTest {

    private val recentPhotoMapper = RecentPhotoEntityMapper()
    private val recentPhotoDao = mock<RecentPhotoDao> {
        on{recentPhotos()} doReturn fakeRecentPhotoEntityListFlow
    }
    private val photoApiService = mock<PictureApiService>()
    private val recentPhotoRepository: RecentPhotoRepository by lazy {
        RecentPhotoRepositoryImpl(
            photoApiService,
            recentPhotoDao,
            recentPhotoMapper
        )
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Test
    fun flow_emits_successfully() = runTest {
        `when`(photoApiService.recentPhoto()).thenAnswer { fakeRecentPhotoEntityResponse }
        recentPhotoRepository.recentPhoto().collect{dataModel ->
            assertEquals(1, (dataModel as Success).data.size)
        }
    }

    @Test
    fun flow_emits_failed() = runTest {
        `when`(photoApiService.recentPhoto()).thenAnswer { fakeRecentPhotoFailedFlow }
        recentPhotoRepository.recentPhoto().collect{dataModel ->
            assertEquals(1, (dataModel as ErrorModel).code)
        }
    }


}