package com.alvaromr.mypunkbeers.di.data.remote

import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun providesBeersRemoteDataSource(beerApiClient: BeerApiClient) =
        BeersRemoteDataSource(beerApiClient)
}