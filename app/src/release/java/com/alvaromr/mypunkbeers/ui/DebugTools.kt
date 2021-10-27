package com.alvaromr.marvel

import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebugTools @Inject constructor() {
    var httpInterceptor: Interceptor? = null

    fun init(app: Context) {
    }

}