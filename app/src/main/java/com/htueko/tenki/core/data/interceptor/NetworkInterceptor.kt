package com.htueko.tenki.core.data.interceptor

import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logInfo
import okhttp3.Interceptor
import okhttp3.Response

/**
 * An interceptor that logs information about network requests and responses.
 * This interceptor is used to inspect the network traffic for debugging and monitoring purposes.
 * It logs the request URL, headers, and the values of each header.
 */
class NetworkInspectorInterceptor : Interceptor {
    private val tag = getClassName<NetworkInspectorInterceptor>()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers
        logInfo(tag, "NetworkInspector Request URL: ${request.url}")
        logInfo(tag, "NetworkInspector Header: $headers")
        for (i in 0 until headers.size) {
           logInfo(tag, "NetworkInspector \t${headers.name(i)}: ${headers.value(i)}")
        }
        return chain.proceed(request)
    }
}