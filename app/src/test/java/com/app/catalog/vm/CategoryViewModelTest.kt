package com.app.catalog.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.catalog.domain.entities.CategoryApiResponse
import com.app.catalog.domain.usecase.CategoryUseCase
import com.app.catalog.domain.entities.CategoryGenerator
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class CategoryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private var categoryUseCase : CategoryUseCase = mock()
    private lateinit var viewModel: CategoryViewModel

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun verifyCategoryLiveDataIsNotEmptyWhenResultIsSuccess() {
        runBlocking {
            givenSuccessResult()
            whenViewModelHandleLoadCategory()
            assertEquals(true, viewModel.category.value?.isNotEmpty())
            assertEquals(1, viewModel.category.value?.size)
        }
    }

    @Test
    fun verifyAreEmptyCategoryLiveData() {
        runBlocking {
            givenSuccessResult(areNecessaryEmptyCategory = true)
            whenViewModelHandleLoadCategory()
            assertEquals(null, viewModel.category.value?.get(0)?.name)
        }
    }

    private fun givenSuccessResult(areNecessaryEmptyCategory : Boolean = false) {
        runBlocking {
            val result: List<CategoryApiResponse> = if (areNecessaryEmptyCategory) {
                listOf(CategoryApiResponse(null,listOf()))
            } else {
                listOf(CategoryGenerator.getSuccessCategoryData())
            }

            given(categoryUseCase.execute()).willReturn(result)
        }
    }

    private fun whenViewModelHandleLoadCategory() {
        viewModel = CategoryViewModel(categoryUseCase)
    }
}