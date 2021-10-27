package com.alvaromr.mypunkbeers.ui

import com.alvaromr.mypunkbeers.App
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebugTools @Inject constructor() {
    var httpInterceptor: Interceptor? = null

    fun init(app: App) = with(app) {
        SoLoader.init(this, false)

        if (FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))

            val networkFlipperPlugin = NetworkFlipperPlugin()
            httpInterceptor = FlipperOkhttpInterceptor(networkFlipperPlugin)
            client.addPlugin(networkFlipperPlugin)

            client.addPlugin(DatabasesFlipperPlugin(this));

            client.start()
        }
    }
}
