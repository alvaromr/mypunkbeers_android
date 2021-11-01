package com.alvaromr.mypunkbeers.domain.model

data class ViewState<T>(
    val loading: Boolean = false,
    val data: T,
)