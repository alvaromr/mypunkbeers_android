package com.alvaromr.mypunkbeers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class EffectChannelHolder<E> : EffectChannelOwner<E> {
    private val _effects = Channel<E>(Channel.BUFFERED)

    override val effects = _effects.receiveAsFlow()

    override fun ViewModel.sendEffect(effect: E) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }
}

