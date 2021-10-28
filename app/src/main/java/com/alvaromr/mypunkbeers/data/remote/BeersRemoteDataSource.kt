package com.alvaromr.mypunkbeers.data.remote

import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRemoteDataSource @Inject constructor(
    private val beerApiClient: BeerApiClient,
    private val remoteMapper: BeerRemoteMapper
) {
    suspend fun searchByName(name: String): List<Beer> =
        beerApiClient.get(
            endpoint = "beers/",
            queryParams = mapOf("beer_name" to name)
        ).map(remoteMapper::toModel)
}