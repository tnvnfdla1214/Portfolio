package com.example.data.di

import com.example.data.service.AudioService
import com.example.data.service.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideBookService(@NetworkModule.KakaoRetrofitClient retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }

    @Singleton
    @Provides
    fun provideAudioService(@NetworkModule.MockRetrofitClient retrofit: Retrofit): AudioService {
        return retrofit.create(AudioService::class.java)
    }
}