package com.app.catalog.domain

import com.app.catalog.datasource.api.CategoryApi
import com.app.catalog.BuildConfig

class CategoryRepository(private val categoryApi : CategoryApi) {

    suspend fun getCategory() = categoryApi.getCategoryAsync(BuildConfig.BASE_URL).await()
}