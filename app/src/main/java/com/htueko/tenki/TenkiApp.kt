package com.htueko.tenki

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.htueko.tenki.core.util.AppLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TenkiApp @Inject constructor() : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        // to setup logging
        if (BuildConfig.DEBUG) {
            AppLogger.init()
        }
    }

    /**
     * New Provides a custom ImageLoader instance for the TenkiApp. The ImageLoader is configured with:
     * - A MemoryCache with a maximum size of 5MB
     * - A DebugLogger for logging during development
     * - Disabling respect for cache headersimage loader
     */
    override fun newImageLoader(): ImageLoader =
        ImageLoader
            .Builder(this)
            .memoryCache {
                MemoryCache
                    .Builder(this)
                    .maxSizeBytes(5 * 1024 * 1024)
                    .build()
            }
            .logger(DebugLogger())
            .respectCacheHeaders(false)
            .build()

}