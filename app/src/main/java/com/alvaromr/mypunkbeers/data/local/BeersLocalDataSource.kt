package com.alvaromr.mypunkbeers.data.local

import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersLocalDataSource @Inject constructor(

){
    fun save(list: List<Beer>) {

    }

    fun getById(id: Int): Beer? = Beer(id, "foo", "bar")
}