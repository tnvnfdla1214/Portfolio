package com.example.portfolio.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.work.WorkManager
import com.example.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient)
//            .baseUrl(Constants.BASE_URL)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): BookSearchApi {
//        return retrofit.create(BookSearchApi::class.java)
//    }

    // Room
//    @Singleton
//    @Provides
//    fun provideBookSearchDatabase(@ApplicationContext context: Context): BookSearchDatabase =
//        Room.databaseBuilder(
//            context.applicationContext,
//            BookSearchDatabase::class.java,
//            "favorite-books",
//        ).build()

    // DataStore
//    @Singleton
//    @Provides
//    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
//        PreferenceDataStoreFactory.create(
//            produceFile = { context.preferencesDataStoreFile(Constants.DATASTORE_NAME) },
//        )

    // WorkManager
//    @Singleton
//    @Provides
//    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
//        WorkManager.getInstance(context)

//    @Singleton
//    @Provides
//    fun provideCacheDeleteResult(): String = "Cache has deleted by Hilt"

//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class APIGsonConverter
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class APIRetrofitClient
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class KakaoRetrofitClient
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class APIOkHttpClient
//
//    @Provides
//    @Singleton
//    @APIGsonConverter
//    fun providesGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
//
//    @Provides
//    @Singleton
//    @APIRetrofitClient
//    fun providesAPIOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                },
//            )
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    @APIRetrofitClient
//    fun providesAPIRetrofit(
//        @APIGsonConverter converterFactory: GsonConverterFactory,
//        @APIRetrofitClient client: OkHttpClient,
//    ): Retrofit {
//        val newClient = client.newBuilder()
//            .addInterceptor(AuthInterceptor())
//            .build()
//        return getRetrofit(ServerUrl.API_URL, converterFactory, newClient)
//    }
//
//    @Provides
//    @Singleton
//    @KakaoRetrofitClient
//    fun providesKakaoRetrofit(
//        @APIGsonConverter converterFactory: GsonConverterFactory,
//        @APIRetrofitClient client: OkHttpClient,
//    ): Retrofit {
//        val newClient = client.newBuilder()
//            .addInterceptor(KakaoAuthInterceptor())
//            .build()
//        return getRetrofit(ServerUrl.KAKAO_BASE_URL, converterFactory, newClient)
//    }
//
//    private fun getRetrofit(
//        url: String,
//        converterFactory: GsonConverterFactory,
//        client: OkHttpClient,
//    ) = Retrofit
//        .Builder()
//        .baseUrl(url)
//        .addConverterFactory(converterFactory)
//        .client(client)
//        .build()

    // DataStore
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(Constants.DATASTORE_NAME) },
        )

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
