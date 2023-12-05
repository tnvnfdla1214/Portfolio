package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Book
import com.example.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteBooksUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    operator fun invoke(): Flow<PagingData<Book>> = bookSearchRepository.getFavoritePagingBooks()
}