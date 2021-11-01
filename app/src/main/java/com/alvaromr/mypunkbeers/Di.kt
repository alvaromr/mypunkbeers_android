package com.alvaromr.mypunkbeers

import com.alvaromr.mypunkbeers.data.local.BeersLocalDataSource
import com.alvaromr.mypunkbeers.data.remote.BeersRemoteDataSource
import com.alvaromr.mypunkbeers.data.remote.api.BeerApiClient
import com.alvaromr.mypunkbeers.data.remote.api.HttpClientBuilder
import com.alvaromr.mypunkbeers.domain.interactor.GetBeerById
import com.alvaromr.mypunkbeers.domain.interactor.SearchBeers
import com.alvaromr.mypunkbeers.domain.repository.BeerRepository
import com.alvaromr.mypunkbeers.ui.navigation.Navigator
import com.alvaromr.mypunkbeers.ui.screen.detail.BeerDetailViewModel
import com.alvaromr.mypunkbeers.ui.screen.list.BeerListViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {

    single { DebugTools() }
}

private val networkModule = module {

    single { HttpClientBuilder(get()) }

    single { BeerApiClient(get()) }

    single { BeersRemoteDataSource(get()) }
}

private val dbModule = module {

    single {
        Database(
            driver = AndroidSqliteDriver(Database.Schema, get(), "mypunkbeers.db")
        ).beerEntityQueries
    }

    single { BeersLocalDataSource(get()) }
}

private val domainModule = module {

    single { BeerRepository(get(), get()) }

    single { SearchBeers(get()) }

    single { GetBeerById(get()) }
}

private val uiModule = module {

    viewModel { Navigator() }

    viewModel { BeerListViewModel(get()) }

    viewModel { BeerDetailViewModel(get()) }
}

val diModules = appModule + networkModule + dbModule + domainModule + uiModule