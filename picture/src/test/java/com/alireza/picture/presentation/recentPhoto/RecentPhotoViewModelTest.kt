package com.alireza.picture.presentation.recentPhoto

import com.alireza.core.data.error.AppError
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.domain.useCase.recentPhoto.RecentPhotoUseCase
import com.alireza.picture.presentation.fakeData.fakeRecentPhotoUseCaseErrorModel
import com.alireza.picture.presentation.fakeData.fakeUseCaseExceptionModel
import com.alireza.picture.presentation.fakeData.fakeRecentPhotoUseCaseModel
import com.alireza.picture.presentation.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RecentPhotoViewModelTest{

    private val recentPhotoUseCase = mock<RecentPhotoUseCase>()
    private val recentPhotoViewModel:RecentPhotoViewModel by lazy { RecentPhotoViewModel(recentPhotoUseCase) }

    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()


    @Test
    fun `get recent photo success`()= runTest {
        `when`(recentPhotoUseCase.invoke(any())).thenReturn(fakeRecentPhotoUseCaseModel)
        // Create an empty collector for the StateFlow
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            recentPhotoViewModel.recentPhotoState.value
        }

        assertEquals( Loading, recentPhotoViewModel.recentPhotoState.value)

        advanceUntilIdle()

        val uiStateSecond = recentPhotoViewModel.recentPhotoState.value
        assertEquals(true, uiStateSecond is RecentPhotoList )

        collectJob.cancel()
    }

    @Test
    fun `get recent photo error`()= runTest {
        `when`(recentPhotoUseCase.invoke(any())).thenReturn(fakeRecentPhotoUseCaseErrorModel)

        recentPhotoViewModel.loadRecentPhoto()
        val data =   recentPhotoViewModel.errorState.take(1).toList()[0]
        assertEquals("fail", (data as ErrorState).message)

    }

    @Test
    fun `get recent photo exception`()= runTest {
        `when`(recentPhotoUseCase.invoke(any())).thenReturn(fakeUseCaseExceptionModel)

        recentPhotoViewModel.loadRecentPhoto()
        val data =   recentPhotoViewModel.errorState.take(1).toList()[0]
        assertEquals(AppError.NetworkConnection::class, (data as ExceptionState).error::class)

    }

}