package com.app.catalog.datasource.api

sealed class NetworkState<T>(val code : Int? = null) {
    class Success<T> : NetworkState<T>()
    class Loading<T> : NetworkState<T>()
    class Error<T>(code : Int) : NetworkState<T>(code)
}

