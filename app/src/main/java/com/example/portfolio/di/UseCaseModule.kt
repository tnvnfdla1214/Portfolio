package com.example.portfolio.di

import com.example.domain.repository.BookSearchRepository
import com.example.domain.usecase.DeleteFavoriteBookUseCase
import com.example.domain.usecase.FetchBookSearchUseCase
import com.example.domain.usecase.GetFavoriteBooksUseCase
import com.example.domain.usecase.SaveFavoriteBookUseCase
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

    @Singleton
    @Provides
    fun bindSaveFavoriteBookUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = SaveFavoriteBookUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindDeleteFavoriteBookUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = DeleteFavoriteBookUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindGetFavoriteBooksUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = GetFavoriteBooksUseCase(bookSearchRepository)
}
