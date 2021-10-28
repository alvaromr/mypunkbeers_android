package com.alvaromr.mypunkbeers.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaromr.mypunkbeers.domain.interactor.SearchBeers
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.ui.EffectChannelHolder
import com.alvaromr.mypunkbeers.ui.EffectChannelOwner
import com.alvaromr.mypunkbeers.ui.StateHolder
import com.alvaromr.mypunkbeers.ui.StateOwner
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val searchBeers: SearchBeers
) : ViewModel(),
    StateOwner<State> by StateHolder(State()),
    EffectChannelOwner<Effect> by EffectChannelHolder() {
    private var currentSearch: Job? = null

    private fun searchBeers() {
        currentSearch?.cancel()
        currentSearch = viewModelScope.launch {
            val query = currentState.viewState.query
            if (query.isNotBlank()) {
                searchBeers(query) {
                    when (it) {
                        is Resource.Loading -> {
                            updateState(loading = true)
                        }
                        is Resource.Success -> {
                            updateState {
                                copy(beers = it.data)
                            }
                        }
                        is Resource.Error -> {
                            updateState()
                            sendEffect(Effect.ErrorToast)
                        }
                    }
                }
            } else {
                updateState {
                    copy(beers = listOf())
                }
            }
        }
    }

    fun triggerEvent(event: Event) = when (event) {
        is Event.QueryChanged -> {
            updateState {
                copy(query = event.query)
            }
            searchBeers()
        }
        is Event.BeerClicked -> {
            sendEffect(Effect.NavigateToBeerDetail(event.beer.id))
        }
    }
}