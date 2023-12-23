package com.example.portfolio.di

import android.content.Context
import androidx.work.WorkManager
import com.example.portfolio.ServerUrl
import com.example.portfolio.network.AuthInterceptor
import com.example.portfolio.network.KakaoAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WorkManagerModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class APIGsonConverter

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class APIRetrofitClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class KakaoRetrofitClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class APIOkHttpClient

    @Provides
    @Singleton
    @APIGsonConverter
    fun providesGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    @APIRetrofitClient
    fun providesAPIOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .followRedirects(true) // Redirect 처리 -> http Redirect 허용
            .followSslRedirects(true) // Ssl Redirect 처리 -> http"s" Redirect 허용
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @APIRetrofitClient
    fun providesAPIRetrofit(
        @APIGsonConverter converterFactory: GsonConverterFactory,
        @APIRetrofitClient client: OkHttpClient,
    ): Retrofit {
        val newClient = client.newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()
        return getRetrofit(ServerUrl.API_URL, converterFactory, newClient)
    }

    @Provides
    @Singleton
    @KakaoRetrofitClient
    fun providesKakaoRetrofit(
        @APIGsonConverter converterFactory: GsonConverterFactory,
        @APIRetrofitClient client: OkHttpClient,
    ): Retrofit {
        val newClient = client.newBuilder()
            .addInterceptor(KakaoAuthInterceptor())
            .build()
        return getRetrofit(ServerUrl.KAKAO_BASE_URL, converterFactory, newClient)
    }

    private fun getRetrofit(
        url: String,
        converterFactory: GsonConverterFactory,
        client: OkHttpClient,
    ) = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()

    // WorkManager
    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideCacheDeleteResult(): String = "Cache has deleted by Hilt"
}

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
