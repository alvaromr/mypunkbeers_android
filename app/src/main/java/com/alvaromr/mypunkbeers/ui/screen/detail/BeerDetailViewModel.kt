package com.alvaromr.mypunkbeers.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaromr.mypunkbeers.domain.interactor.GetBeerById
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.ui.EffectChannelHolder
import com.alvaromr.mypunkbeers.ui.EffectChannelOwner
import com.alvaromr.mypunkbeers.ui.StateHolder
import com.alvaromr.mypunkbeers.ui.StateOwner
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBeerById: GetBeerById,
) : ViewModel(),
    StateOwner<State> by StateHolder(State()),
    EffectChannelOwner<Effect> by EffectChannelHolder() {

    val id: Int = savedStateHandle.get<String>(BeerDetailScreenDirection.KEY_ID)?.toInt() ?: -1

    init {
        viewModelScope.launch {
            getBeerById(id) {
                when (it) {
                    is Resource.Loading -> {
                        updateState(loading = true)
                    }
                    is Resource.Success -> {
                        updateState {
                            copy(beer = it.data)
                        }
                    }
                    is Resource.Error -> {
                        updateState()
                        sendEffect(Effect.NotFoundToast)
                    }
                }
            }
        }
    }

    fun triggerEvent(event: Event) = when (event) {
        is Event.Back -> {
            sendEffect(Effect.NavigateBack)
        }
    }
}