package com.alvaromr.mypunkbeers.di.domain.repository

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesBeerRepository(
        beersRemoteDataSource: BeersRemoteDataSource,
        beersLocalDataSource: BeersLocalDataSource,
    ) =
        BeerRepository(
            beersRemoteDataSource = beersRemoteDataSource,
            beersLocalDataSource = beersLocalDataSource
        )
}