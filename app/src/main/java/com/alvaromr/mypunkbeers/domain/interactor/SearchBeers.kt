package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.Logger
import com.alvaromr.mypunkbeers.domain.model.BeerList
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class SearchBeers(
    private val beerRepository: BeerRepository,
) {
    suspend operator fun invoke(
        name: String,
        offset: Int,
        block: suspend (value: Resource<out BeerList>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        delay(SEARCH_DELAY)
        beerRepository.searchByName(name, offset).catch {
            Logger.e(it)
            emit(Resource.Error(type = "search error"))
        }.collect {
            Logger.i(it.beers.toString())
            emit(Resource.Success(it))
        }
    }.collect(block)

    companion object {
        const val SEARCH_DELAY = 500L
    }
}