package com.alvaromr.mypunkbeers.ui.screen.detail

import com.alvaromr.mypunkbeers.domain.model.Beer

class BeerDetailContract {
    data class State(
        val beer: Beer? = null
    )

    sealed class Event {
        class BeerIdSet(val id: Int) : Event()
        object Back : Event()
    }

    sealed class Effect {
        object NavigateBack : Effect()
        object NotFoundToast : Effect()
    }
}