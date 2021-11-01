package com.alvaromr.mypunkbeers.di

import com.alvaromr.mypunkbeers.DebugTools
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesDebugTools() = DebugTools()
}