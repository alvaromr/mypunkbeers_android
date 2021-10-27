package com.alvaromr.mypunkbeers.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alvaromr.mypunkbeers.domain.model.DataState
import kotlin.reflect.KProperty

interface ViewStateOwner<S> {
    val currentState: DataState<S>
}

class ViewStateHolder<S>(
    initialState: S
) : ViewStateOwner<S> {

    private val _state: MutableState<DataState<S>> =
        mutableStateOf(DataState(viewState = initialState))

    private var state: DataState<S>
        get() = _state.value
        set(value) {
            _state.value = value
        }

    override val currentState: DataState<S> get() = state

    fun setState(block: (DataState<S>.() -> DataState<S>)) {
        state = state.block()
    }

    operator fun getValue(thisRef: ViewStateOwner<S>, property: KProperty<*>) = currentState
}