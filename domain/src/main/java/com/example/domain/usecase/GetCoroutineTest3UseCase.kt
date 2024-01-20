package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetCoroutineTest3UseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, CoroutineTest>() {

    override suspend fun doWork(params: Unit?): ResultStatus<CoroutineTest> {
        return try {
            coroutineScope {
                val user = async { coroutineTestRepository.getUserName() }
                val userNews = coroutineTestRepository.getUserNews(user.await())

                ResultStatus.Success(userNews)
            }
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}