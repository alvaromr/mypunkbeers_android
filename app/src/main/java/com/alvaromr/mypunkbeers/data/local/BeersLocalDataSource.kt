package com.alvaromr.mypunkbeers.data.local

import com.alvaromr.mypunkbeers.data.local.db.BeerEntityQueries
import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersLocalDataSource @Inject constructor(
    private val beerEntityQueries: BeerEntityQueries,
) {
    fun save(list: List<Beer>) {
        list.forEach { beer ->
            with(beer) {
                beerEntityQueries.insertOne(
                    id = id.toLong(),
                    name = name,
                    subtitle = subtitle,
                    description = description,
                    imageUrl = imageUrl,
                )
            }
        }
    }

    fun getById(id: Int): Beer? =
        beerEntityQueries.findById(id = id.toLong()).executeAsOneOrNull()?.run {
            Beer(
                id = this.id.toInt(),
                name = name,
                subtitle = subtitle,
                description = description,
                imageUrl = imageUrl,
            )
        }

}