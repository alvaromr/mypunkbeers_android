package com.alvaromr.mypunkbeers.data.remote

import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import com.alvaromr.mypunkbeers.domain.model.BeerList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRemoteDataSource @Inject constructor(
    private val beerApiClient: BeerApiClient,
    private val remoteMapper: BeerRemoteMapper
) {
    suspend fun searchByName(name: String, offset: Int): BeerList {
        val list = beerApiClient.get(
            endpoint = "beers/",
            queryParams = mapOf(
                "beer_name" to name,
                "page" to (offset / LIMIT + 1).toString(),
                "per_page" to LIMIT.toString()
            )
        ).map(remoteMapper::toModel)
        return BeerList(list, (list.size < LIMIT))
    }

    companion object {
        const val LIMIT = 30
    }
}