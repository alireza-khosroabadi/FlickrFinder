package com.alireza.picture.data.repository.searchHistory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.picture.data.fakeData.fakeSearchHistoryEntityList
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.domain.model.searchHistory.SearchHistoryMapper
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchHistoryRepositoryImplTest {

    private val mapper: SearchHistoryMapper = SearchHistoryMapper()
    private val searchHistoryDao = mock<SearchHistoryDao>()
    private val searchHistoryRepository: SearchHistoryRepository by lazy {
        SearchHistoryRepositoryImpl(
            searchHistoryDao
        )
    }


    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun `fetch search history success`() = runTest {
        `when`(searchHistoryDao.recentSearch()).thenAnswer {
            flowOf(fakeSearchHistoryEntityList)
        }

        searchHistoryRepository.searchHistory().collect{data ->
            assertEquals("2", data[1].query)
        }
    }

    @Test
    fun `call remove history in dao success`() = runTest{
        val entity = SearchHistoryEntity("test")
        searchHistoryRepository.removeHistory(entity)
        verify(searchHistoryDao).delete(entity)

    }

    @Test
    fun `call clearTable in dao success`() = runTest{
        searchHistoryRepository.clearAllHistory()
        verify(searchHistoryDao).clearTable()

    }

}