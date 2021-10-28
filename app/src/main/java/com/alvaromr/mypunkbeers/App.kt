package com.alvaromr.mypunkbeers

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {
    @Inject
    lateinit var debugTools: DebugTools

    override fun onCreate() {
        super.onCreate()
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
