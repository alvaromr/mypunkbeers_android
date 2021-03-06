package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.Logger
import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetBeerById(
    private val beerRepository: BeerRepository,
) {
    suspend operator fun invoke(
        id: Int,
        block: suspend (value: Resource<out Beer?>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        beerRepository.getById(id).catch {
            Logger.e(it)
            emit(Resource.Error(type = "not found error"))
        }.collect {
            Logger.i(it.toString())
            emit(Resource.Success(it))
        }
    }.collect(block)
}