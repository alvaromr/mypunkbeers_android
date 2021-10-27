package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchBeers @Inject constructor(
    private val beerRepository: BeerRepository,
) {
    suspend operator fun invoke(
        name: String,
        block: suspend (value: Resource<out List<Beer>>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        delay(1000) //TODO: delete when loading is implemented
        beerRepository.searchByName(name).catch {
            emit(Resource.Error(type = "search error"))
        }.collect {
            emit(Resource.Success(it))
        }
    }.collect(block)
}