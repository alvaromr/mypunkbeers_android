package com.alvaromr.mypunkbeers.ui.screen.list

import com.alvaromr.mypunkbeers.domain.model.Beer

class BeerListContract {
    data class State(
        val beers: List<Beer> = listOf(),
        val query: String = ""
    )

    sealed class Event {
        class QueryChanged(val query: String): Event()
    }
}