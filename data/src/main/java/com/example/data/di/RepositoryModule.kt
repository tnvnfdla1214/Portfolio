package com.example.data.di

import com.example.data.repository.BookSearchRepositoryImpl
import com.example.data.repository.MusicRepositoryImpl
import com.example.domain.repository.BookSearchRepository
import com.example.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        bookSearchRepositoryImpl: BookSearchRepositoryImpl,
    ): BookSearchRepository

    @Singleton
    @Binds
    abstract fun bindMusicRepository(
        musicRepositoryImpl: MusicRepositoryImpl,
    ): MusicRepository
}