package com.example.portfolio.di

import com.example.domain.repository.BookSearchRepository
import com.example.domain.repository.CoroutineTestRepository
import com.example.domain.repository.MusicRepository
import com.example.domain.usecase.DeleteFavoriteBookUseCase
import com.example.domain.usecase.FetchBookSearchUseCase
import com.example.domain.usecase.GetCoroutineTest0UseCase
import com.example.domain.usecase.GetCoroutineTest1UseCase
import com.example.domain.usecase.GetCoroutineTest2GetNewsUseCase
import com.example.domain.usecase.GetCoroutineTest2GetUserUseCase
import com.example.domain.usecase.GetCoroutineTest3UseCase
import com.example.domain.usecase.GetCoroutineTest4ExceptionUseCase
import com.example.domain.usecase.GetDeleteModeUseCase
import com.example.domain.usecase.GetFavoriteBooksUseCase
import com.example.domain.usecase.GetMusicListUseCase
import com.example.domain.usecase.GetSortModeUseCase
import com.example.domain.usecase.SaveDeleteModeUseCase
import com.example.domain.usecase.SaveFavoriteBookUseCase
import com.example.domain.usecase.SaveSortModeUseCase
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

    @Singleton
    @Provides
    fun bindSaveSortModeUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = SaveSortModeUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindGetSortModeUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = GetSortModeUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindSaveDeleteModeUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = SaveDeleteModeUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindGetDeleteModeUseCase(
        bookSearchRepository: BookSearchRepository,
    ) = GetDeleteModeUseCase(bookSearchRepository)

    @Singleton
    @Provides
    fun bindGetMusicListUseCase(
        musicRepository: MusicRepository,
    ) = GetMusicListUseCase(musicRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest0UseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest0UseCase(coroutineTestRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest1UseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest1UseCase(coroutineTestRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest2GetUserUseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest2GetUserUseCase(coroutineTestRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest2GetNewsUseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest2GetNewsUseCase(coroutineTestRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest3UseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest3UseCase(coroutineTestRepository)

    @Singleton
    @Provides
    fun bindGetCoroutineTest4ExceptionUseCase(
        coroutineTestRepository: CoroutineTestRepository,
    ) = GetCoroutineTest4ExceptionUseCase(coroutineTestRepository)
}
