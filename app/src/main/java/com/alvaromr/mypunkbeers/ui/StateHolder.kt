package com.alvaromr.mypunkbeers.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alvaromr.mypunkbeers.domain.model.DataState

class StateHolder<S>(
    initialState: S
) : StateOwner<S> {
    private val _state: MutableState<DataState<S>> =
        mutableStateOf(DataState(viewState = initialState))

    private var state: DataState<S>
        get() = _state.value
        set(value) {
            _state.value = value
        }

    override val currentState: DataState<S> get() = state

    override fun updateState(loading: Boolean, block: S.() -> S) {
        state = state.copy(
            viewState = state.viewState.block(),
            loading = loading
        )
    }
}