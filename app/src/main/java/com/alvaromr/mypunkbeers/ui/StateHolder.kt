package com.alvaromr.mypunkbeers.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alvaromr.mypunkbeers.domain.model.ViewState

class StateHolder<S>(
    initialState: S,
) : StateOwner<S> {
    private val _state: MutableState<ViewState<S>> =
        mutableStateOf(ViewState(data = initialState))

    private var state: ViewState<S>
        get() = _state.value
        set(value) {
            _state.value = value
        }

    override val viewState: ViewState<S> get() = state

    override fun updateState(loading: Boolean, block: S.() -> S) {
        state = state.copy(
            data = state.data.block(),
            loading = loading
        )
    }
}