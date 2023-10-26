package com.example.portfolio.network

import com.example.portfolio.network.Constants.AUTHORIZATION
import com.example.portfolio.network.Constants.KAKAO_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class KakaoAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().header(AUTHORIZATION, KAKAO_API_KEY).build()

        return chain.proceed(newRequest)
    }
}
