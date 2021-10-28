package com.alvaromr.mypunkbeers.data.local.db

import android.content.Context
import com.alvaromr.mypunkbeers.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbHiltModule {
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) = Database(
        driver = AndroidSqliteDriver(
            Database.Schema,
            context,
            "mypunkbeers.db"
        )
    )

    @Provides
    fun providesBeerEntityQueries(database: Database): BeerEntityQueries = database.beerEntityQueries

}