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

        categoryRepository.getCategory().let { categoryEntity ->

            val response = categoryRepository.getCategory()

            if(response.isSuccessful) {
                categoryEntity.body()?.let {

                    val items = response.body()
                    if(items?.size!! >= 0) category = items
                }

                networkState.postValue(NetworkState.Success())
            }
            else networkState.postValue(NetworkState.Error(response.code()))
        }
        return category
    }

    fun getNetworkState(): MutableLiveData<NetworkState<Int>> = networkState
}