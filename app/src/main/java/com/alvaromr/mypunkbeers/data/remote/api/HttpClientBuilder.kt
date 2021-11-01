package com.alvaromr.mypunkbeers.data.remote.api

import com.alvaromr.mypunkbeers.DebugTools
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json as KotlinJson

class HttpClientBuilder(
    private val debugTools: DebugTools,
) {
    fun httpClient() = HttpClient(OkHttp) {
        engine {
            debugTools.httpInterceptor?.let {
                addInterceptor(it)
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(KotlinJson {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }
}