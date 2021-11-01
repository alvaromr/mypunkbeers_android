package com.alvaromr.mypunkbeers.data.remote.api

import io.ktor.client.request.*

class BeerApiClient(
    private val httpClientBuilder: HttpClientBuilder,
) {

    suspend fun get(endpoint: String, queryParams: Map<String, String>): List<BeerApiDto> {
        val client = httpClientBuilder.httpClient()
        val response: List<BeerApiDto> = client.get("$BASE_URL$endpoint") {
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
        return response
    }

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}