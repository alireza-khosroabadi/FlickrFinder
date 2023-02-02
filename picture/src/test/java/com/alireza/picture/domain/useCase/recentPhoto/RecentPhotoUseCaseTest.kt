package com.alireza.picture.domain.useCase.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alireza.core.data.repository.DataModel
import com.alireza.core.data.repository.ErrorModel
import com.alireza.core.data.repository.ExceptionModel
import com.alireza.core.data.repository.Success
import com.alireza.core.domain.model.UseCaseModel
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityList
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import com.alireza.picture.domain.model.recentPhoto.RecentPhotoMapper
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import com.alireza.picture.domain.useCase.recentPhoto.RecentPhotoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import java.util.concurrent.TimeoutException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RecentPhotoUseCaseTest{

private val recentPhotoRepository = mock<RecentPhotoRepository>()
private val recentPhotoUseCase: RecentPhotoUseCase by lazy {
    RecentPhotoUseCase(recentPhotoRepository, RecentPhotoMapper())
}


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()



    @Test
    fun `flow emits successfully`() = runTest {
        val repositoryResponse =flow { emit(Success(fakeRecentPhotoEntityList)) }

        `when`(recentPhotoRepository.recentPhoto()).thenReturn( repositoryResponse )

        recentPhotoUseCase.invoke(Unit).collect{useCaseModel ->
            assertEquals(
                "1",
                (useCaseModel as UseCaseModel.Success).data[0].id
            )
        }

    }

    @Test
    fun `flow emits with failed`() = runTest {

        `when`(recentPhotoRepository.recentPhoto()).thenAnswer {
            flow<DataModel<List<RecentPhotoEntity>>> {
                emit(ErrorModel(fakeRecentPhotoEntityList,1, "error"))
            }
        }

        recentPhotoUseCase.invoke(Unit).collect { useCaseModel ->
            assertEquals(1, (useCaseModel as UseCaseModel.Error).code)
        }
    }

    @Test
    fun `flow emits with HTTP error`() = runTest {

        `when`(recentPhotoRepository.recentPhoto()).thenAnswer {
            flow<DataModel<List<RecentPhotoEntity>>> {
                emit(
                    ExceptionModel(
                        TimeoutException("TimeOutException")
                    )
                )
            }
        }

        recentPhotoUseCase.invoke(Unit).collect { useCaseModel ->
            assertEquals("TimeOutException", (useCaseModel as UseCaseModel.Exception).exception.message)
        }
    }
}