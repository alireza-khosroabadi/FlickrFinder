package com.alireza.picture.data.repository.photoDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.Success
import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.fakeData.fakePhotoDetailResponse
import com.alireza.picture.data.fakeData.fakeSearchPhotoFailed
import com.alireza.picture.data.param.photoDetail.PhotoDetailParam
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.domain.repository.photoDetail.PhotoDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PhotoDetailRepositoryImplTest{

    private val networkConnectivity = mock<NetworkConnectivity>()
    private val apisService = mock<PictureApiService>()
    private val photoDetailRepository: PhotoDetailRepository by lazy {
        PhotoDetailRepositoryImpl(networkConnectivity, apisService)
    }


    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun `fetch photo detail with id success`() = runTest {
        `when`(networkConnectivity.isInternetOn()).thenReturn(true)
        `when`(apisService.photoDetail(any())).thenAnswer { fakePhotoDetailResponse}

        photoDetailRepository.photoDetail(PhotoDetailParam("")).collect{ dataModel ->
            assertEquals("Laura Beswick", (dataModel as Success).data.owner?.realName)
        }
    }

    @Test
    fun `throw internet connection exception`() = runTest {
        `when`(networkConnectivity.isInternetOn()).thenReturn(false)
        assertThrows(
            InternetConnectionException::class.java
        ) { photoDetailRepository.photoDetail(PhotoDetailParam("")) }
    }

}