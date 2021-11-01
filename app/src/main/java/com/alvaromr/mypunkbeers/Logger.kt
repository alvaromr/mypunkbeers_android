package com.alvaromr.mypunkbeers

import timber.log.Timber

object Logger {
    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun e(throwable: Throwable) = Timber.e(throwable)

    fun i(message: String) = Timber.i(message)
}