package com.app.catalog.vm

import androidx.lifecycle.MutableLiveData
import com.app.catalog.commons.base.BaseViewModel
import com.app.catalog.datasource.api.NetworkState
import com.app.catalog.domain.usecase.CategoryUseCase
import com.app.catalog.domain.entities.CategoryApiResponse
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryUseCase : CategoryUseCase) : BaseViewModel() {

    // OBSERVABLES ---
    var networkState : MutableLiveData<NetworkState<Int>> = MutableLiveData()
    val category : MutableLiveData<List<CategoryApiResponse>> = MutableLiveData()

    // UTILS ---
    init {
        handleCategoryLoad()
    }

    private fun handleCategoryLoad() {
        ioScope.launch { updateCategoryLiveData(categoryUseCase.execute()) }
        networkState = categoryUseCase.getNetworkState()
    }

    private fun updateCategoryLiveData(result: List<CategoryApiResponse>)
            = category.postValue(result)
}