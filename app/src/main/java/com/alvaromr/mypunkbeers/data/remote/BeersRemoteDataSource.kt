package com.alvaromr.mypunkbeers.data.remote

import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRemoteDataSource @Inject constructor(

) {
    fun searchByName(name: String): List<Beer> {
        return Array(5) { Beer(it, name) }.toList()
    }
}