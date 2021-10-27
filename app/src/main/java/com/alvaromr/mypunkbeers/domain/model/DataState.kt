package com.alvaromr.mypunkbeers.domain.model

data class DataState<T>(
    val loading: Boolean = false,
    val viewState: T,
)