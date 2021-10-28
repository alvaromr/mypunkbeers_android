package com.alvaromr.mypunkbeers.ui

import com.alvaromr.mypunkbeers.domain.model.DataState

interface StateOwner<S> {
    val currentState: DataState<S>
    fun updateState(loading: Boolean = false, block: S.() -> S = { currentState.viewState })
}