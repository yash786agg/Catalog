package com.app.catalog.domain

import com.app.catalog.datasource.api.CategoryApi

class CategoryRepository(private val categoryApi : CategoryApi) {

    suspend fun getCategory() = categoryApi.getCategoryAsync().await()
}