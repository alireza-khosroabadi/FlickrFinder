package com.alireza.picture.presentation.searchPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import com.alireza.picture.domain.repository.searchPhoto.SearchPhotoRepository
import com.alireza.picture.domain.useCase.searchHistory.ClearSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.RemoveSearchHistoryUseCase
import com.alireza.picture.domain.useCase.searchHistory.SearchHistoryListUseCase
import com.alireza.picture.domain.useCase.searchPhoto.SearchPhotoUseCase
import com.alireza.picture.presentation.fakeData.fakeSearchHistoryUseCaseModel
import com.alireza.picture.presentation.fakeData.fakeSearchPhotoUseCaseModel
import com.alireza.picture.presentation.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchPhotoViewModelTest {

    private val searchPhotoUseCase = mock<SearchPhotoUseCase>()
    private val searchHistoryListUseCase = mock<SearchHistoryListUseCase>()
    private val searchHistoryRepository = mock<SearchHistoryRepository>()

    private val removeSearchHistoryUseCase:RemoveSearchHistoryUseCase by lazy { RemoveSearchHistoryUseCase(searchHistoryRepository) }
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase by lazy { ClearSearchHistoryUseCase(searchHistoryRepository) }

    private val searchPhotoViewModel: SearchPhotoViewModel by lazy {
        SearchPhotoViewModel(
            searchPhotoUseCase,
            searchHistoryListUseCase,
            removeSearchHistoryUseCase,
            clearSearchHistoryUseCase
        )
    }


    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()




    @Test
    fun `load past search history query success`()= runTest {
        `when`(searchHistoryListUseCase.invoke(any())).thenReturn(fakeSearchHistoryUseCaseModel)

        // Create an empty collector for the StateFlow
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
           searchPhotoViewModel.searchHistoryState.value
        }


//        val historyStateLoading = searchPhotoViewModel.searchHistoryState.value
        assertEquals(Loading, searchPhotoViewModel.searchHistoryState.value)

        advanceUntilIdle()

        val historyStateList = searchPhotoViewModel.searchHistoryState.value
        assertEquals(true, historyStateList is SearchHistoryList)
        assertEquals(2, (historyStateList as SearchHistoryList).lastHistory.size)

        // Cancel the collecting job at the end of the test
        collectJob.cancel()
    }

    @Test
    fun `search photo success`()= runTest {
        `when`(searchPhotoUseCase.invoke(any())).thenReturn(fakeSearchPhotoUseCaseModel)

        searchPhotoViewModel.searchPhoto("Sea")
        val data =   searchPhotoViewModel.searchPhotoState.take(2).toList()[1]
        assertEquals(1, (data as SearchPhotoList).photoList.size)
    }


    @Test
    fun `invoke repository clearHistory function to clear all history`()= runTest {

        searchPhotoViewModel.clearAllHistory()

        verify(searchHistoryRepository).clearAllHistory()
    }

}