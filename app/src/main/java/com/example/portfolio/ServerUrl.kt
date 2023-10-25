package com.example.portfolio

import android.content.Context

object ServerUrl {
    var KAKAO_BASE_URL = "https://dapi.kakao.com/"
    var API_URL = "https://portfolio.com/"

    fun init(context: Context) {
        KAKAO_BASE_URL = context.getString(R.string.kakao_base_url)
        API_URL = context.getString(R.string.api_base_url)
    }

    fun getAuthUrl(context: Context): String {
        return "accessKey 붙인거"
    }
}
