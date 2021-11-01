package com.alvaromr.mypunkbeers.di.data.remote.api

import com.alvaromr.mypunkbeers.DebugTools
import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import com.alvaromr.mypunkbeers.data.remote.api.HttpClientBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun providesHttpClientBuilder(debugTools: DebugTools) =
        HttpClientBuilder(debugTools)

    @Provides
    @Singleton
    fun providesBeerApiClient(httpClientBuilder: HttpClientBuilder) =
        BeerApiClient(httpClientBuilder)
}