package com.app.catalog.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.app.catalog.datasource.api.NetworkState
import com.app.catalog.domain.entities.CategoryApiResponse
import com.app.catalog.domain.CategoryRepository

class CategoryUseCase(private val categoryRepository: CategoryRepository) {

    // FOR DATA ---
    private val networkState = MutableLiveData<NetworkState<Int>>()

    suspend fun execute(): List<CategoryApiResponse> {

        networkState.postValue(NetworkState.Loading())

        var category : List<CategoryApiResponse> = listOf()

        val response = categoryRepository.getCategory()

        if(response.isSuccessful) {
            val items = response.body()
            if(items?.size!! >= 0) category = items

            networkState.postValue(NetworkState.Success(response.code()))
        }
        else networkState.postValue(NetworkState.Error(response.code()))

        return category
    }

    fun getNetworkState(): MutableLiveData<NetworkState<Int>> = networkState
}