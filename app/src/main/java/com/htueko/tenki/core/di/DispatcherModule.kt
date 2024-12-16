package com.htueko.tenki.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    /**
     * Provides the default CoroutineDispatcher.
     *
     * The default dispatcher is used by Kotlin Coroutine library to run coroutine tasks in the
     * background when there is no specific dispatcher specified.
     *
     * This dispatcher is backed by a shared pool of threads and is used for I/O-bound tasks that do
     * not need to run on the main thread.
     *
     * @return The default CoroutineDispatcher.
     */
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    /**
     * Provides the IO CoroutineDispatcher.
     *
     * The IO dispatcher is used by Kotlin Coroutine library for offloading I/O-bound tasks.
     * It is backed by a shared pool of threads and is used for tasks that perform blocking I/O,
     * such as reading from or writing to a file.
     *
     * @return The IO CoroutineDispatcher.
     */
    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides the Main CoroutineDispatcher.
     *
     * The main dispatcher is used by Kotlin Coroutine library to run coroutine tasks on the main
     * thread. It is backed by the Android UI thread and is used for tasks that require access to
     * the Android UI components or need to interact with the UI thread.
     *
     * @return The Main CoroutineDispatcher.
     */
    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

/**
 * @IoDispatcher
 *
 * This annotation is used to qualify the IO CoroutineDispatcher provided by DispatcherModule.
 *
 * The IO dispatcher is the coroutine dispatcher that is used by Kotlin Coroutine library for
 * offloading I/O-bound tasks. It is backed by a shared pool of threads and is used for tasks that
 * perform blocking I/O, such as reading from or writing to a file.
 *
 * @see DispatcherModule
 * @see CoroutineDispatcher
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

/**
 * @MainDispatcher
 *
 * This annotation is used to qualify the Main CoroutineDispatcher provided by DispatcherModule.
 *
 * The main dispatcher is the coroutine dispatcher that is used by Kotlin Coroutine library to run
 * coroutine tasks on the main thread.
 *
 * This dispatcher is backed by the Android UI thread and is used for tasks that require access to
 * the Android UI components or need to interact with the UI thread.
 *
 * @see DispatcherModule
 * @see CoroutineDispatcher
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

/**
 * @DefaultDispatcher
 *
 * This annotation is used to qualify the Default CoroutineDispatcher provided by DispatcherModule.
 *
 * The default dispatcher is the coroutine dispatcher that is used by Kotlin Coroutine library to run
 * coroutine tasks in the background when there is no specific dispatcher specified.
 *
 * This dispatcher is backed by a shared pool of threads and is used for I/O-bound tasks that do not
 * need to run on the main thread.
 *
 * @see DispatcherModule
 * @see CoroutineDispatcher
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey