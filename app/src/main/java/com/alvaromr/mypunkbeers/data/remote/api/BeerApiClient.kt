package com.alvaromr.mypunkbeers.data.remote.api

import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerApiClient @Inject constructor(
    private val httpClientBuilder: HttpClientBuilder,
) {
    private val baseUrl: String = "https://api.punkapi.com/v2/"

    suspend fun get(
        endpoint: String,
        queryParams: Map<String, String>,
    ): List<BeerApiDto> {
        val client = httpClientBuilder.httpClient()
        val response: List<BeerApiDto> = client.get("$baseUrl$endpoint") {
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
        return response
    }
}