package com.alireza.picture.data.repository.searchPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.Success
import com.alireza.core.tools.NetworkConnectivity
import com.alireza.picture.data.fakeData.fakeSearchPhotoEntityResponse
import com.alireza.picture.data.fakeData.fakeSearchPhotoFailed
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.data.param.searchPhoto.SearchPhotoParam
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchPhotoRepositoryImplTest {

    private val pictureApiService = mock<PictureApiService>()
    private val searchHistoryDao = mock<SearchHistoryDao>()
    private val networkConnectivity = mock<NetworkConnectivity>()

    private val searchPhotoRepository: SearchPhotoRepository by lazy {
        SearchPhotoRepositoryImpl(networkConnectivity, pictureApiService, searchHistoryDao)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun `flow emits successfully`() = runTest {
        `when`(networkConnectivity.isInternetOn()).thenReturn(true)
        `when`(pictureApiService.searchPhoto(any()))
            .thenAnswer { fakeSearchPhotoEntityResponse }
        searchPhotoRepository.searchPhoto(SearchPhotoParam("")).collect { dataModel ->
            assertEquals(1, (dataModel as Success).data.size)
        }
    }

    @Test
    fun `flow emits failed`() = runTest {
        `when`(networkConnectivity.isInternetOn()).thenReturn(true)
        `when`(pictureApiService.searchPhoto(any())).thenAnswer { fakeSearchPhotoFailed }
        searchPhotoRepository.searchPhoto(SearchPhotoParam("")).collect { dataModel ->
            assertEquals(1, (dataModel as ErrorModel).code)
        }
    }


    @Test
    fun `verify search history dao insert on search`() = runTest {
        val searchParam = SearchPhotoParam("")
        val entity = SearchHistoryEntity(searchParam.query)
        `when`(networkConnectivity.isInternetOn()).thenReturn(true)
        `when`(pictureApiService.searchPhoto(any())).thenAnswer { fakeSearchPhotoFailed }
        searchPhotoRepository.searchPhoto(searchParam).collect { Unit}
        verify(searchHistoryDao).insert(entity)
    }

    @Test
    fun `throw internet connection exception`() = runTest {
        `when`(networkConnectivity.isInternetOn()).thenReturn(false)
        assertThrows(
            InternetConnectionException::class.java
        ) { searchPhotoRepository.searchPhoto(SearchPhotoParam("")) }
    }
}