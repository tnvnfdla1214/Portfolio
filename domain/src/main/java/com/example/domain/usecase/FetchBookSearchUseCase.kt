package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Book
import com.example.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow

class FetchBookSearchUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    operator fun invoke(query: String, sort: String): Flow<PagingData<Book>> =
        bookSearchRepository.searchBooksPaging(query, sort)
}