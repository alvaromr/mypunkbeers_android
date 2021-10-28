package com.alvaromr.mypunkbeers.domain.repository

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.domain.model.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepository @Inject constructor(
    private val beersRemoteDataSource: BeersRemoteDataSource,
    private val beersLocalDataSource: BeersLocalDataSource,
) {
    suspend fun searchByName(name: String): Flow<List<Beer>> = flow {
        val list = beersRemoteDataSource.searchByName(name)
        beersLocalDataSource.save(list)
        emit(list)
    }

    suspend fun getById(id: Int): Flow<Beer?> = flow {
        emit(beersLocalDataSource.getById(id))
    }
}