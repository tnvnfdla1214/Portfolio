package com.example.domain.usecase

import com.example.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.first

class GetDeleteModeUseCase(
    private val bookSearchRepository: BookSearchRepository,
) {
    suspend operator fun invoke() = bookSearchRepository.getCacheDeleteMode().first()
}