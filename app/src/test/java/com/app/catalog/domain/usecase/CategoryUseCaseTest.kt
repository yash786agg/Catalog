package com.app.catalog.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.catalog.commons.util.ConstantTest.Companion.EXPECTED_ERROR_RESPONSE_CODE
import com.app.catalog.commons.util.ConstantTest.Companion.EXPECTED_SUCCESS_RESPONSE_CODE
import com.app.catalog.datasource.api.NetworkState
import com.app.catalog.domain.CategoryRepository
import com.app.catalog.domain.entities.CategoryGenerator
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class CategoryUseCaseTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val categoryRepository: CategoryRepository = mock()
    private val categoryUseCase = CategoryUseCase(categoryRepository)

    @Test
    fun verifyResultWhenRepoMockReturnSuccessState() {

        runBlocking {

            val result = Response.success(listOf(CategoryGenerator.getEmptyCategoryData()))
            given(categoryRepository.getCategory())
                .willReturn(result)

            val realResult = categoryUseCase.execute()
            val expectedResult = Response.success(listOf(CategoryGenerator.getEmptyCategoryData()))

            assertEquals(expectedResult.body(), realResult)
        }
    }

    @Test
    fun verifyUseCaseCallRepository() {

        val response = Response.success(listOf(CategoryGenerator.getEmptyCategoryData()))

        runBlocking {
            given(categoryRepository.getCategory())
                .willReturn(response)

            categoryUseCase.execute()

            verify(categoryRepository, times(1)).getCategory()
        }
    }

    @Test
    fun verifyIsLoadingLiveDataWhenResultIsSuccess() {
        val expectedResult = 200
        categoryUseCase.getNetworkState().value = NetworkState.Success(expectedResult)
        assertEquals(
            (categoryUseCase.getNetworkState().value as NetworkState.Success<Int>).code,
            EXPECTED_SUCCESS_RESPONSE_CODE
        )
    }

    @Test
    fun verifyIsLoadingLiveDataWhenResultIsError() {
        val expectedResult = 404
        categoryUseCase.getNetworkState().value = NetworkState.Error(expectedResult)
        assertEquals(
            (categoryUseCase.getNetworkState().value as NetworkState.Error<Int>).code,
            EXPECTED_ERROR_RESPONSE_CODE
        )
    }
}