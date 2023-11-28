package com.example.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class ResultUseCase<in Params, R> {

    operator fun invoke(params: Params): Flow<ResultStatus<R>> = flow {
        emit(ResultStatus.Loading)
        emit(doWork(params))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    protected abstract suspend fun doWork(params: Params): ResultStatus<R>
}
fun <T> Flow<ResultStatus<T>>.doWork(
    scope: CoroutineScope,
    isLoading: () -> Unit,
    isSuccess: (T) -> Unit,
    isError: (Throwable) -> Unit,
) {
    scope.launch {
        collect { status ->
            when (status) {
                is ResultStatus.Loading -> {
                    isLoading()
                }

                is ResultStatus.Success<T> -> {
                    isSuccess(status.data)
                }

                is ResultStatus.Error -> {
                    isError(status.throwable)
                }
            }
        }
    }
}