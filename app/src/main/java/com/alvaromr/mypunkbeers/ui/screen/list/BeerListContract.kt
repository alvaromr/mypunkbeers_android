package com.alvaromr.mypunkbeers.ui.screen.list

import com.alvaromr.mypunkbeers.domain.model.Beer

class BeerListContract {
    data class State(
        val beers: List<Beer> = listOf(),
        val query: String = ""
    )

    sealed class Event {
        class QueryChanged(val query: String) : Event()
        class BeerClicked(val beer: Beer) : Event()
    }

    sealed class Effect {
        class NavigateToBeerDetail(val id: Int) : Effect()
        object ErrorToast : Effect()
    }
}