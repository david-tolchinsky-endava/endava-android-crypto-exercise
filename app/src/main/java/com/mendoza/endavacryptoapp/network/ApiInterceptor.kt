package com.mendoza.endavacryptoapp.network

import com.mendoza.endavacryptoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY)
        return chain.proceed(requestBuilder.build())
    }
}