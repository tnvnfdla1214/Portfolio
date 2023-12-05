package com.example.domain.usecase

import com.example.domain.model.Book
import com.example.domain.repository.BookSearchRepository

class SaveFavoriteBookUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    suspend operator fun invoke(book: Book) {
        bookSearchRepository.insertBooks(book)
    }
}