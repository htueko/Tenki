package com.htueko.tenki.core.util

import timber.log.Timber

/**
 * Logs a debug message with the specified tag and message.
 *
 * @param tag The tag to associate with the log message.
 * @param message The message to log.
 */
fun logDebug(
    tag: String,
    message: String,
) {
    Timber.tag(tag).d("$tag $message")
}


/**
 * Logs an informational message with the specified tag and message.
 *
 * @param tag The tag to associate with the log message.
 * @param message The message to log.
 */
fun logInfo(
    tag: String,
    message: String,
) {
    Timber.tag(tag).i("$tag $message")
}

/**
 * Logs an error message with the specified tag and message.
 *
 * @param tag The tag to associate with the log message.
 * @param message The message to log.
 */
fun logError(
    tag: String,
    message: String,
) {
    Timber.tag(tag).e("$tag $message")
}