package com.example.domain.usecase

import com.example.domain.repository.BookSearchRepository

class SaveSortModeUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    suspend operator fun invoke(mode: String) {
        bookSearchRepository.saveSortMode(mode)
    }
}