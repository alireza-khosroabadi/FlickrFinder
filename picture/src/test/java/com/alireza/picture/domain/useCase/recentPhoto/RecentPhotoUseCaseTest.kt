package com.alireza.picture.domain.useCase.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.data.error.AppError
import com.alireza.core.data.error.InternetConnectionException
import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityList
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.domain.model.recentPhoto.RecentPhotoMapper
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import java.net.SocketTimeoutException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RecentPhotoUseCaseTest{

private val recentPhotoRepository = mock<RecentPhotoRepository>()
private val recentPhotoUseCase: RecentPhotoUseCase by lazy {
    RecentPhotoUseCase(recentPhotoRepository, RecentPhotoMapper())
}


    @get:Rule
    val rule = InstantTaskExecutorRule()



    @Test
    fun `flow emits successfully`() = runTest {
        val repositoryResponse =flow { emit(Success(fakeRecentPhotoEntityList)) }

        `when`(recentPhotoRepository.recentPhoto( any())).thenReturn( repositoryResponse )

        recentPhotoUseCase.invoke(ShouldFetchParam()).collect{ useCaseModel ->
            assertEquals(
                "1",
                (useCaseModel as UseCaseModel.Success).data[0].id
            )
        }

    }

    @Test
    fun `flow emits with failed`() = runTest {

        `when`(recentPhotoRepository.recentPhoto( any())).thenAnswer {
            flow<DataModel<List<RecentPhotoEntity>>> {
                emit(ErrorModel(fakeRecentPhotoEntityList,1, "error"))
            }
        }

        recentPhotoUseCase.invoke(ShouldFetchParam()).collect { useCaseModel ->
            assertEquals(1, (useCaseModel as UseCaseModel.Error).code)
        }
    }

    @Test
    fun `flow emits with HTTP error`() = runTest {
        `when`(recentPhotoRepository.recentPhoto(any())).thenAnswer {
            flow<DataModel<List<RecentPhotoEntity>>> {
                emit(
                    ExceptionModel(
                        SocketTimeoutException("TimeOutException")
                    )
                )
            }
        }

        recentPhotoUseCase.invoke(ShouldFetchParam()).collect { useCaseModel ->
            assertEquals(AppError.SocketTimeOut::class, (useCaseModel as UseCaseModel.Exception).error::class)
        }
    }

    @Test
    fun `throw InternetConnectionException and return AppError_INTERNET_CONNECTION`() = runTest{
        doThrow(InternetConnectionException()).`when`(recentPhotoRepository).recentPhoto(any())

        recentPhotoUseCase.invoke(ShouldFetchParam()).collect{ useCaseModel ->
            assertEquals(AppError.NetworkConnection::class, (useCaseModel as UseCaseModel.Exception).error::class)
        }
    }

    @Test
    fun `throw SocketTimeoutException and return AppError_SOCKET_TIME_OUT`() = runTest {
        `when`(recentPhotoRepository.recentPhoto(any())).thenAnswer {
            throw SocketTimeoutException()
        }

        recentPhotoUseCase.invoke(ShouldFetchParam()).collect { useCaseModel ->
            assertEquals(AppError.SocketTimeOut::class, (useCaseModel as UseCaseModel.Exception).error::class)
        }
    }
}