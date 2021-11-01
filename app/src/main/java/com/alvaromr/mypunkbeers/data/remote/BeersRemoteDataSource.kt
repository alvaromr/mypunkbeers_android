package com.alvaromr.mypunkbeers.data.remote

import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import com.alvaromr.mypunkbeers.data.remote.api.BeerApiDto
import com.alvaromr.mypunkbeers.domain.model.Beer
import com.alvaromr.mypunkbeers.domain.model.BeerList

class BeersRemoteDataSource(
    private val beerApiClient: BeerApiClient,
) {
    suspend fun searchByName(name: String, offset: Int): BeerList {
        val list = beerApiClient.get(
            endpoint = "beers/",
            queryParams = mapOf(
                "beer_name" to name,
                "page" to (offset / LIMIT + 1).toString(),
                "per_page" to LIMIT.toString()
            )
        ).map(::toModel)
        return BeerList(list, (list.size < LIMIT))
    }

    private fun toModel(dto: BeerApiDto) = with(dto) {
        Beer(
            id = id,
            name = name,
            subtitle = tagline,
            description = description,
            alcoholByVolume = abv,
            imageUrl = image_url
        )
    }

    companion object {
        const val LIMIT = 30
    }
}