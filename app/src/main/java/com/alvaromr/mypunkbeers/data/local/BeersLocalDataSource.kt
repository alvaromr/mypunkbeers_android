package com.alvaromr.mypunkbeers.data.local

import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersLocalDataSource @Inject constructor(

){
    private val cache = mutableSetOf<Beer>()

    fun save(list: List<Beer>) {
        cache.addAll(list)
    }

    fun getById(id: Int): Beer? = cache.find { it.id == id }
}