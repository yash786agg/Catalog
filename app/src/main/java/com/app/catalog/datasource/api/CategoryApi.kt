package com.app.catalog.datasource.api

import com.app.catalog.domain.entities.CategoryApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CategoryApi {

    @GET
    fun getCategoryAsync(@Url url : String) : Deferred<Response<List<CategoryApiResponse>>>
}