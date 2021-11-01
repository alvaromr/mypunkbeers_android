package com.alvaromr.mypunkbeers.data.local

import com.alvaromr.mypunkbeers.data.local.db.BeerEntityQueries
import com.alvaromr.mypunkbeers.data.local.db.Beer_Entity
import com.alvaromr.mypunkbeers.domain.model.Beer

class BeersLocalDataSource(
    private val beerEntityQueries: BeerEntityQueries,
) {
    fun save(list: List<Beer>) {
        list.forEach { it.save() }
    }

    fun getById(id: Int): Beer? =
        beerEntityQueries.findById(id = id.toLong()).executeAsOneOrNull()?.toModel()

    private fun Beer_Entity.toModel() = Beer(
        id = this.id.toInt(),
        name = name,
        subtitle = subtitle,
        description = description,
        alcoholByVolume = alcoholByVolume,
        imageUrl = imageUrl,
    )

    private fun Beer.save() {
        beerEntityQueries.insertOne(
            id = id.toLong(),
            name = name,
            subtitle = subtitle,
            description = description,
            alcoholByVolume = alcoholByVolume,
            imageUrl = imageUrl,
        )
    }
}