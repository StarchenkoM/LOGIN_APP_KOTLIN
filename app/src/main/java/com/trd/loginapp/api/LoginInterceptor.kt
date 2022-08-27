package com.trd.loginapp.api

import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("HEADER1", "header value")
            .build()
        return chain.proceed(request)
    }
}