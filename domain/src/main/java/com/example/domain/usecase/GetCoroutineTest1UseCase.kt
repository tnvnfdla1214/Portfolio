package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetCoroutineTest1UseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, List<CoroutineTest>>() {

    override suspend fun doWork(params: Unit?): ResultStatus<List<CoroutineTest>> {
        return try {
            coroutineScope {
                val result: MutableList<CoroutineTest> = mutableListOf()

                val user = async { coroutineTestRepository.getUser() }
                val news = async { coroutineTestRepository.getNews() }

                result.add(news.await())
                result.add(user.await())

                ResultStatus.Success(result)
            }
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}