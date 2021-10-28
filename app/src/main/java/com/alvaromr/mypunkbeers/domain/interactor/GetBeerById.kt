package com.alvaromr.mypunkbeers.domain.interactor

import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.Resource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeerById @Inject constructor(
    private val beerRepository: BeerRepository,
) {
    suspend operator fun invoke(
        id: Int,
        block: suspend (value: Resource<out Beer?>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        beerRepository.getById(id).catch {
            emit(Resource.Error(type = "not found error"))
        }.collect {
            emit(Resource.Success(it))
        }
    }.collect(block)
}