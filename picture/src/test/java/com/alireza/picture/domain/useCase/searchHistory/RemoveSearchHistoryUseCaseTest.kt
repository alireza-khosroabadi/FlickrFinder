package com.alireza.picture.domain.useCase.searchHistory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.picture.data.local.dao.searchHistory.SearchHistoryDao
import com.alireza.picture.data.local.entity.searchHistory.SearchHistoryEntity
import com.alireza.picture.data.repository.searchHistory.SearchHistoryRepositoryImpl
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.repository.searchHistory.SearchHistoryRepository
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
class RemoveSearchHistoryUseCaseTest{

    private val searchHistoryDao = mock<SearchHistoryDao>()
    private val searchHistoryRepository: SearchHistoryRepository by lazy {
        SearchHistoryRepositoryImpl(searchHistoryDao)
    }
    private val removeSearchHistoryUseCase: RemoveSearchHistoryUseCase by lazy {
        RemoveSearchHistoryUseCase(searchHistoryRepository)
    }


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `call clearTable from SearchHistoryDao`() = runTest {
        val searchHistory = SearchHistory("")
        val entity = SearchHistoryEntity(searchHistory.query)
        removeSearchHistoryUseCase.invoke(searchHistory)
        verify(searchHistoryDao).delete(entity)
    }
}