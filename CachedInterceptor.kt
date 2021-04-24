package ru.sovcombank.essn.util.network


import okhttp3.Interceptor
import okhttp3.Response

class CachedInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (request.method() == "GET") {
            chain.proceed(request).newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", String.format("max-age=%d", 24 * 60 * 60))
                    .build()
        } else {
            chain.proceed(request)
        }
    }
}