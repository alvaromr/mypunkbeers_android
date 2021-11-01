package com.alvaromr.mypunkbeers.di.data.local

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.local.db.BeerEntityQueries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun providesBeersLocalDataSource(beerEntityQueries: BeerEntityQueries) =
        BeersLocalDataSource(beerEntityQueries)
}