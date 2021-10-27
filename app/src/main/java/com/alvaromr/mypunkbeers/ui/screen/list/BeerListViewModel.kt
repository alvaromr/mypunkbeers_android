package com.alvaromr.mypunkbeers.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaromr.mypunkbeers.domain.interactor.SearchBeers
import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.DataState
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.ui.ViewStateHolder
import com.alvaromr.mypunkbeers.ui.ViewStateOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val searchBeers: SearchBeers
) : ViewModel(), ViewStateOwner<List<Beer>> {
    private val viewStateHolder: ViewStateHolder<List<Beer>> = ViewStateHolder(listOf())

    override val currentState: DataState<List<Beer>> by viewStateHolder

    init {
        viewModelScope.launch {
            searchBeers("foo") {
                when (it) {
                    is Resource.Loading -> {
                        viewStateHolder.setState {
                            copy(loading = true)
                        }
                    }
                    is Resource.Success -> {
                        viewStateHolder.setState {
                            copy(viewState = it.data, loading = false)
                        }
                    }
                    is Resource.Error -> {
                        viewStateHolder.setState {
                            copy(loading = false)
                        }
                    }
                }
            }
        }
    }
}