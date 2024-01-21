package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository

class GetCoroutineTest4ExceptionUseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, List<CoroutineTest>>() {

    override suspend fun doWork(params: Unit?): ResultStatus<List<CoroutineTest>> {
        return try {
            val result: MutableList<CoroutineTest> = mutableListOf()
            result.add(coroutineTestRepository.getUser())
            result.add(coroutineTestRepository.getCoroutineException())

            ResultStatus.Success(result)
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}