package com.alvaromr.mypunkbeers.di.domain.interactor

import com.alvaromr.mypunkbeers.domain.interactor.GetBeerById
import com.alvaromr.mypunkbeers.domain.interactor.SearchBeers
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Provides
    @Singleton
    fun providesGetBeerById(beerRepository: BeerRepository) =
        GetBeerById(beerRepository)

    @Provides
    @Singleton
    fun providesSearchBeers(beerRepository: BeerRepository) =
        SearchBeers(beerRepository)
}