package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository

class GetCoroutineTest2GetUserUseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, CoroutineTest>() {

    override suspend fun doWork(params: Unit?): ResultStatus<CoroutineTest> {
        return try {
            ResultStatus.Success(coroutineTestRepository.getUser())
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}