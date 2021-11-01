package com.alvaromr.mypunkbeers.domain.repository

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.domain.model.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BeerRepository(
    private val beersRemoteDataSource: BeersRemoteDataSource,
    private val beersLocalDataSource: BeersLocalDataSource,
) {
    suspend fun searchByName(name: String, offset: Int) = flow {
        val response = beersRemoteDataSource.searchByName(name, offset)
        beersLocalDataSource.save(response.beers)
        emit(response)
    }

    suspend fun getById(id: Int): Flow<Beer?> = flow {
        emit(beersLocalDataSource.getById(id))
    }
}