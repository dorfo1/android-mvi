package com.example.hilt_sample.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor constructor() : Interceptor {

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        req = req.newBuilder()
            .addHeader(AUTHORIZATION,"token")
            .build()


        return chain.proceed(req)
    }
}