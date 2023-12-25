package com.example.data.di

import com.example.data.dataSource.MusicDataSource
import com.example.data.service.AudioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideMusicDataSource(audioService: AudioService): MusicDataSource {
        return MusicDataSource(audioService)
    }
}