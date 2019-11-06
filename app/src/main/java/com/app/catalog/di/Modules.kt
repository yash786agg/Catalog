package com.app.catalog.di

import com.app.catalog.datasource.api.CategoryApi
import com.app.catalog.BuildConfig
import com.app.catalog.domain.usecase.CategoryUseCase
import com.app.catalog.datasource.network.createNetworkClient
import com.app.catalog.domain.CategoryRepository
import com.app.catalog.vm.CategoryViewModel
import com.app.catalog.commons.utils.UiHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(viewModelModule,
            repositoryModule,
            networkModule,
            useCaseModule,
            uiHelperModule)
    )
}

val viewModelModule = module {
    viewModel { CategoryViewModel(categoryUseCase = get()) }
}

val repositoryModule = module {
    single { CategoryRepository(categoryApi = get()) }
}

val useCaseModule = module {
    single { CategoryUseCase(categoryRepository = get()) }
}

val networkModule = module {
    single { CATEGORY_API }
}

val uiHelperModule = module {
    single { UiHelper(androidContext()) }
}

private val retrofit : Retrofit = createNetworkClient(BuildConfig.BASE_URL)

private val CATEGORY_API : CategoryApi = retrofit.create(CategoryApi::class.java)