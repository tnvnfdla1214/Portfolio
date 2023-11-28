package com.example.portfolio.di

import com.example.domain.repository.BookSearchRepository
import com.example.domain.usecase.FetchBookSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun bindFetchBookSearchUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = FetchBookSearchUseCase(bookSearchRepository)
}
