package com.alireza.picture.domain.useCase.searchPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.data.fakeData.fakeSearchPhotoEntityResponse
import com.alireza.picture.data.fakeData.fakeSearchPhotoFailed
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.data.param.SearchPhotoParam
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.data.repository.searchPhoto.SearchPhotoRepositoryImpl
import com.alireza.picture.domain.model.searchPhoto.SearchPhotoMapper
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchPhotoUseCaseTest{

    private val mapper = SearchPhotoMapper()
    private val apiService = mock<PictureApiService>()
    private val searchHistoryDao = mock<SearchHistoryDao>()
    private val searchPhotoUseCase: SearchPhotoUseCase by lazy {
        SearchPhotoUseCase(searchPhotoRepository, mapper)
    }
    private val searchPhotoRepository: SearchPhotoRepository by lazy {
        SearchPhotoRepositoryImpl(apiService,searchHistoryDao)
    }


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Test
    fun `search photo successfully`() = runTest {
        val searchParam = SearchPhotoParam("")
        `when`(apiService.searchPhoto("")).thenAnswer {
            fakeSearchPhotoEntityResponse
        }
        searchPhotoUseCase.invoke(searchParam).collect{data ->
            assertEquals(1, (data as UseCaseModel.Success).data.size)
        }

    }

    @Test
    fun `search photo api error`() = runTest {
        val searchParam = SearchPhotoParam("")
        `when`(apiService.searchPhoto("")).thenAnswer { fakeSearchPhotoFailed}
        searchPhotoUseCase.invoke(searchParam).collect{data ->
            assertEquals("fail to fetch data", (data as UseCaseModel.Error).message)
        }

    }

    @Test
    fun `search photo insert history call`() = runTest {
        val searchParam = SearchPhotoParam("")
        val entity = SearchHistoryEntity(searchParam.query)
        `when`(apiService.searchPhoto("")).thenAnswer { fakeSearchPhotoEntityResponse}
        searchPhotoUseCase.invoke(searchParam).collect{Unit}
        verify(searchHistoryDao).insert(entity)

    }
}