package com.htueko.tenki

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.memory.MemoryCache
import coil3.util.DebugLogger
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
class TenkiApp @Inject constructor() : Application(), SingletonImageLoader.Factory {

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
     *
     * @return A pre-configured [ImageLoader] for use throughout the app.
     */
    override fun newImageLoader(context: Context): ImageLoader =
        ImageLoader
            .Builder(context)
            .memoryCache {
                MemoryCache
                    .Builder()
                    .maxSizeBytes(5 * 1024 * 1024)
                    .build()
            }
            .logger(DebugLogger())
            .build()

}