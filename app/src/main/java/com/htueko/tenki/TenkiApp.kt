package com.htueko.tenki

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.htueko.tenki.core.util.AppLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Main application class for the Tenki Weather project.
 *
 * This class serves as the entry point for the application and is responsible for:
 * - Initializing [Hilt] for dependency injection.
 * - Setting up global logging via [AppLogger] during debug builds.
 * - Providing a custom [ImageLoader] configuration for [Coil].
 *
 * @constructor Creates an instance of the TenkiApp. The [Inject] annotation allows Hilt
 * to handle the instantiation.
 */
@HiltAndroidApp
class TenkiApp @Inject constructor() : Application(), ImageLoaderFactory {

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * * Configures [AppLogger] if the build variant is [BuildConfig.DEBUG].
     */
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            AppLogger.init()
        }
    }

    /**
     * Provides a custom [ImageLoader] instance for the application to optimize image loading.
     * * The configuration includes:
     * - **Memory Cache**: Limited to 5MB to balance performance and memory footprint.
     * - **Logging**: Uses [DebugLogger] to monitor image requests during development.
     * - **Cache Headers**: [ImageLoader.Builder.respectCacheHeaders] is set to `false`
     * to prioritize local loading speed over server-side directives.
     *
     * @return A pre-configured [ImageLoader] for use throughout the app.
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