package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.data.db.BookSearchDatabase
import com.example.data.service.BookSearchApi
import com.example.data.util.Constants
import com.example.data.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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

    // Retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .followRedirects(true) // Redirect 처리 -> http Redirect 허용
            .followSslRedirects(true) // Ssl Redirect 처리 -> http"s" Redirect 허용
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BookSearchApi {
        return retrofit.create(BookSearchApi::class.java)
    }

    // Room
    @Singleton
    @Provides
    fun provideBookSearchDatabase(@ApplicationContext context: Context): BookSearchDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            BookSearchDatabase::class.java,
            "favorite-books",
        ).build()

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(Constants.DATASTORE_NAME) },
        )
}

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L