package com.app.catalog.domain

import com.app.catalog.domain.entities.CategoryGenerator
import com.app.catalog.commons.util.ConstantTest.Companion.CATEGORY_NAME
import com.app.catalog.commons.util.ConstantTest.Companion.PRODUCT_AMOUNT
import com.app.catalog.commons.util.ConstantTest.Companion.PRODUCT_CURRENCY
import com.app.catalog.commons.util.ConstantTest.Companion.PRODUCT_ID
import com.app.catalog.commons.util.ConstantTest.Companion.PRODUCT_IMAGE_URL
import com.app.catalog.commons.util.ConstantTest.Companion.PRODUCT_NAME
import com.app.catalog.commons.util.SequenceList
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import retrofit2.Response

@RunWith(JUnit4::class)
class CategoryRepositoryTest {

    private val categoryRepository: CategoryRepository = mock()

    @Test
    fun verifyApiModelToEntityModelMustReturnSameValues() {

        val response = Response.success(CategoryGenerator.getSuccessCategoryData())

        runBlocking {
            `when`(categoryRepository.getCategory()).thenAnswer(SequenceList(listOf(response)))
        }

        val expectedResult = Response.success(CategoryGenerator.getSuccessCategoryData())

        assertEquals(expectedResult.body(), response.body())

        assertEquals(2, response.body()?.products?.size)
        assertEquals(CATEGORY_NAME, response.body()?.name)
        assertTrue(response.body()?.products?.get(0)?.productId == PRODUCT_ID)
        assertTrue(response.body()?.products?.get(1)?.productName == PRODUCT_NAME)
        assertNotNull("", response.body()?.products?.get(1)?.productImageUrl)
        assertFalse(response.body()?.products?.get(1)?.productImageUrl == PRODUCT_IMAGE_URL)
        assertFalse(response.body()?.products?.get(0)?.productPrice?.productAmount == PRODUCT_AMOUNT)
        assertTrue(response.body()?.products?.get(1)?.productPrice?.productCurrency == PRODUCT_CURRENCY)
    }

    @Test
    fun verifyResultWhenRepoMockReturnEmptyState() {

        val response = Response.success(CategoryGenerator.getEmptyCategoryData())

        runBlocking {
            `when`(categoryRepository.getCategory()).thenAnswer(SequenceList(listOf(response)))
        }

        val expectedResult = Response.success(CategoryGenerator.getEmptyCategoryData())

        assertEquals(expectedResult.body(), response.body())

        assertEquals(0, response.body()?.products?.size)
        assertEquals(null,response.body()?.name)
        response.body()?.products?.isNullOrEmpty()?.let { assertTrue(it) }
        response.body()?.products?.isNotEmpty()?.let { assertFalse(it) }
    }
}