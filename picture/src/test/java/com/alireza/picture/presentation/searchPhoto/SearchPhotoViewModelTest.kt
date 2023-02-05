package com.alireza.picture.presentation.searchPhoto

import com.alireza.core.data.error.AppError
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.domain.useCase.searchHistory.ClearSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.RemoveSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.SearchHistoryListUseCase
import com.alireza.picture.domain.useCase.searchPhoto.SearchPhotoUseCase
import com.alireza.picture.presentation.fakeData.*
import com.alireza.picture.presentation.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchPhotoViewModelTest {

    private val searchPhotoUseCase = mock<SearchPhotoUseCase>()
    private val historyPhotoUseCase = mock<SearchHistoryListUseCase>()
    private val removeHistoryListUseCase = mock<RemoveSearchHistoryUseCase>()
    private val clearSearchHistoryUseCase = mock<ClearSearchHistoryUseCase>()
    private val searchPhotoViewModel: SearchPhotoViewModel by lazy {
        SearchPhotoViewModel(
            searchPhotoUseCase,
            historyPhotoUseCase,
            removeHistoryListUseCase,
            clearSearchHistoryUseCase
        )
    }

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Test
    fun `get search photo success`() = runTest {
        Mockito.`when`(searchPhotoUseCase.invoke(any())).thenReturn(fakeSearchPhotoUseCaseModel)

        searchPhotoViewModel.searchPhoto("")
        val data = searchPhotoViewModel.searchPhotoState.take(2).toList()[1]
        assertEquals(1, (data as SearchPhotoList).photoList.size)
    }

    @Test
    fun `get search photo error`() = runTest {
        Mockito.`when`(searchPhotoUseCase.invoke(any()))
            .thenReturn(fakeSearchPhotoUseCaseErrorModel)

        searchPhotoViewModel.searchPhoto("")
        val data = searchPhotoViewModel.errorState.take(1).toList()[0]
        assertEquals("fail", (data as ErrorState).message)

    }

    @Test
    fun `get search photo exception`() = runTest {
        Mockito.`when`(searchPhotoUseCase.invoke(any()))
            .thenReturn(fakeUseCaseExceptionModel)

        searchPhotoViewModel.searchPhoto("")
        val data = searchPhotoViewModel.errorState.take(1).toList()[0]
        assertEquals(AppError.NetworkConnection::class, (data as ExceptionState).error::class)

    }
}