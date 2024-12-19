package com.htueko.tenki.core.util

import timber.log.Timber

object AppLogger {
    fun init() {
        setupTimber()
    }

    /**
     * to log using Timber library
     * @see [https://github.com/JakeWharton/timber]
     */
    private fun setupTimber() {
        Timber.plant(
            object : Timber.DebugTree() {
                /**
                 * Override [log] to modify the tag and add a "tenki" prefix to it.
                 * You can rename the String "tenki_tag_" as you see fit.
                 */
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?,
                ) {
                    super.log(priority, "tenki_$tag", message, t)
                }

                /**
                 * Override [createStackElementTag] to include a append a "method name" to the tag.
                 */
                override fun createStackElementTag(element: StackTraceElement): String =
                    String.format(
                        "%s:%s",
                        element.className,
                        element.methodName,
                        element.lineNumber,
                        super.createStackElementTag(element),
                    )
            },
        )
    }
}