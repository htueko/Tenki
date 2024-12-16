package com.htueko.tenki.core.util

import androidx.compose.ui.Modifier
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

/**
 * to get the name of the class.
 *
 * @return [String] name of the class, eg getClassName<TomAndJerry>() return "TomAndJerry"
 */
inline fun <reified T> getClassName(): String = T::class.java.simpleName

/**
 * Apply if
 *
 * @param condition condition to set the modifier. example if true apply this modifier else not.
 * @param modifier
 * @receiver
 * @return Modifier.
 */
inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier =
    if (condition) {
        then(modifier(this))
    } else {
        this
    }