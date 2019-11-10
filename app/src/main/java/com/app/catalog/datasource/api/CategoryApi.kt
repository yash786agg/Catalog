package com.app.catalog.datasource.api

import com.app.catalog.BuildConfig
import com.app.catalog.domain.entities.CategoryApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {

    @GET(BuildConfig.BASE_URL)
    fun getCategoryAsync() : Deferred<Response<List<CategoryApiResponse>>>
}