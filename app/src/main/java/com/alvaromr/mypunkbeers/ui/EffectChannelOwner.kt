package com.alvaromr.mypunkbeers.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

interface EffectChannelOwner<E> {
    val effects: Flow<E>
    fun ViewModel.sendEffect(effect: E)
}