package com.example.portfolio.network

import com.example.data.network.Constants.ACCESSKEY
import com.example.data.network.Constants.HEADER_ACCESSKEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder().header(ACCESSKEY, HEADER_ACCESSKEY).build()
        return chain.proceed(newRequest)
    }
}
