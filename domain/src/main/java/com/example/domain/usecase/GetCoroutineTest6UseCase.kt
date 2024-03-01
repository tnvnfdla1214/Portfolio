package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

class GetCoroutineTest6UseCase(
    private val coroutineTestRepository: CoroutineTestRepository,
) : ResultUseCase<Unit, CoroutineTest>() {
//    private val supervisorJob = SupervisorJob()
//    private val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)
//
//    override suspend fun doWork(params: Unit?): ResultStatus<CoroutineTest> {
//        return try {
//            withContext(coroutineScope.coroutineContext) {
//                coroutineTestRepository.getUser()
//            }
//            ResultStatus.Success(coroutineTestRepository.getNews())
//        } catch (throwable: Throwable) {
//            ResultStatus.Error(throwable)
//        }
//    }
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