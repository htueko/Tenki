package com.htueko.tenki.core.data.interceptor

import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logInfo
import okhttp3.Interceptor
import okhttp3.Response

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