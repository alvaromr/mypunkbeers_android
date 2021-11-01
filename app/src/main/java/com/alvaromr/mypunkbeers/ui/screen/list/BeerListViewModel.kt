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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BeerListViewModel(
    private val searchBeers: SearchBeers,
) : ViewModel(),
    StateOwner<State> by StateHolder(State()),
    EffectChannelOwner<Effect> by EffectChannelHolder() {
    private var currentSearch: Job? = null

    fun triggerEvent(event: Event) = when (event) {
        is Event.QueryChanged -> {
            updateState {
                copy(query = event.query, beers = listOf(), maxScroll = 1)
            }
            searchBeers()
        }
        is Event.BeerClicked -> {
            sendEffect(Effect.NavigateToBeerDetail(event.beer.id))
        }
        is Event.ListScrolledToEndPosition -> {
            onListScrollerToEndPosition(event.position)
        }
    }

    private fun searchBeers() {
        currentSearch?.cancel()
        currentSearch = viewModelScope.launch {
            val query = viewState.data.query
            if (query.isNotBlank()) {
                searchBeers(query, viewState.data.maxScroll) {
                    when (it) {
                        is Resource.Loading -> {
                            updateState(loading = true)
                        }
                        is Resource.Success -> {
                            updateState {
                                copy(
                                    beers = beers.toMutableList().apply { addAll(it.data.beers) },
                                    endReached = it.data.endReached
                                )
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

    private fun onListScrollerToEndPosition(position: Int) {
        val dataState = viewState
        if (!dataState.loading && position >= dataState.data.beers.lastIndex &&
            position >= dataState.data.maxScroll && !dataState.data.endReached
        ) {
            val maxScroll = position + 1
            updateState {
                copy(maxScroll = maxScroll)
            }
            searchBeers()
        }
    }
}