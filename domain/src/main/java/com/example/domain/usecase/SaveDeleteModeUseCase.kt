package com.example.domain.usecase

import com.example.domain.repository.BookSearchRepository

class SaveDeleteModeUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    suspend operator fun invoke(value: Boolean) {
        bookSearchRepository.saveCacheDeleteMode(value)
    }
}