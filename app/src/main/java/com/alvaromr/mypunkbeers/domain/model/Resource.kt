package com.alvaromr.mypunkbeers.domain.model

sealed class Resource<T> {
    object Loading : Resource<Nothing>()
    data class Error(val type: String) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
}