package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository

class GetCoroutineTest6UseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, CoroutineTest>() {
    override suspend fun doWork(params: Unit?): ResultStatus<CoroutineTest> {
        return try {
            runCatching {
                coroutineTestRepository.getUser()
            }
            ResultStatus.Success(coroutineTestRepository.getNews())
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}