package com.alvaromr.mypunkbeers

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), ImageLoaderFactory {
    private val debugTools: DebugTools by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(diModules)
        }

        debugTools.init(this)
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .crossfade(true)
        .okHttpClient {
            OkHttpClient.Builder().apply {
                debugTools.httpInterceptor?.let {
                    addInterceptor(it)
                }
            }.build()
        }.build()
}
