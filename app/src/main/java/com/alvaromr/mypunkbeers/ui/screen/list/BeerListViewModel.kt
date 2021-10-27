package com.alvaromr.mypunkbeers.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaromr.mypunkbeers.domain.interactor.SearchBeers
import com.alvaromr.mypunkbeers.domain.model.DataState
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.ui.ViewStateHolder
import com.alvaromr.mypunkbeers.ui.ViewStateOwner
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListContract.Event
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val searchBeers: SearchBeers
) : ViewModel(), ViewStateOwner<State> {
    private val viewStateHolder: ViewStateHolder<State> = ViewStateHolder(State())

    override val currentState: DataState<State> by viewStateHolder

    private var currentSearch: Job? = null

    init {
        searchBeers()
    }

    private fun searchBeers() {
        currentSearch?.cancel()
        currentSearch = viewModelScope.launch {
            val query = currentState.viewState.query
            if (query.isNotBlank()) {
                searchBeers(query) {
                    when (it) {
                        is Resource.Loading -> {
                            viewStateHolder.updateState(loading = true)
                        }
                        is Resource.Success -> {
                            viewStateHolder.updateState {
                                copy(beers = it.data)
                            }
                        }
                        is Resource.Error -> {
                            viewStateHolder.updateState()
                        }
                    }
                }
            } else {
                viewStateHolder.updateState {
                    copy(beers = listOf())
                }
            }
        }
    }

    fun triggerEvent(event: Event) = when (event) {
        is Event.QueryChanged -> {
            viewStateHolder.updateState {
                copy(query = event.query)
            }
            searchBeers()
        }
    }
}