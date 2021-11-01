package com.alvaromr.mypunkbeers.ui

import com.alvaromr.mypunkbeers.domain.model.ViewState

interface StateOwner<S> {
    val viewState: ViewState<S>
    fun updateState(loading: Boolean = false, block: S.() -> S = { viewState.data })
}