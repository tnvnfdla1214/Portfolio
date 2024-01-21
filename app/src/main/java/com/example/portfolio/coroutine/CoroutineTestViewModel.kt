package com.example.portfolio.coroutine

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.model.CoroutineTest
import com.example.domain.usecase.GetCoroutineTest0UseCase
import com.example.domain.usecase.GetCoroutineTest1UseCase
import com.example.domain.usecase.GetCoroutineTest2GetNewsUseCase
import com.example.domain.usecase.GetCoroutineTest2GetUserUseCase
import com.example.domain.usecase.GetCoroutineTest3UseCase
import com.example.domain.usecase.GetCoroutineTest4ExceptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTest0UseCase: GetCoroutineTest0UseCase,
    private val getCoroutineTest1UseCase: GetCoroutineTest1UseCase,
    private val getCoroutineTest2GetUserUseCase: GetCoroutineTest2GetUserUseCase,
    private val getCoroutineTest2GetNewsUseCase: GetCoroutineTest2GetNewsUseCase,
    private val getCoroutineTest3UseCase: GetCoroutineTest3UseCase,
    private val getCoroutineTest4ExceptionUseCase: GetCoroutineTest4ExceptionUseCase,
) : ViewModel() {
    val testItem: MutableLiveData<CoroutineTest> = MutableLiveData()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    init {
        //test0()
        // test1()
        // test2()
        // test3()
        test4()
    }

    /**
     * 2가지의 API를 순차적으로 호출 하고 그 결과를 합쳐서 보여준다.
     * API를 받은 이후에 다음 API를 호출하기 때문에 시간이 낭비 된다.
     */
    private fun test0() {
        testItem.value = CoroutineTest("시작", LocalDateTime.now().format(formatter))
        getCoroutineTest0UseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = {
                it.forEach { i -> testItem.value = i }
                testItem.value = CoroutineTest("끝", LocalDateTime.now().format(formatter))
            },
            isError = { },

        )
    }

    /**
     * 2가지의 API를 동시에 호출하고 그 결과를 합쳐서 보여준다.
     * 동시에 호출 되기 때문에 시간이 낭비 되지 않는다.
     */
    private fun test1() {
        testItem.value = CoroutineTest("시작", LocalDateTime.now().format(formatter))
        getCoroutineTest1UseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = {
                it.forEach { i -> testItem.value = i }
                testItem.value = CoroutineTest("끝", LocalDateTime.now().format(formatter))
            },
            isError = { },

        )
    }

    /**
     * API를 동시에 호출하고 그 결과를 보여준다.
     * 와면이 중단된다면 그 이후의 API는 호출 되지 않으므로 불필요한 API 호출을 막을 수 있다.
     * (getUser의 시간을 1초로 바꿔야 함)
     */
    private fun test2() {
        testItem.value = CoroutineTest("시작", LocalDateTime.now().format(formatter))
        getUser()
        getNews()
    }

    /**
     * 2가지의 API를 순차적으로 호출 하고 그 결과를 합쳐서 보여준다.
     * 순서를 보장 받기 때문에 순서가 중요한 경우 사용한다.
     */
    private fun test3() {
        testItem.value = CoroutineTest("시작", LocalDateTime.now().format(formatter))
        getCoroutineTest3UseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = {
                testItem.value = it
                testItem.value = CoroutineTest("끝", LocalDateTime.now().format(formatter))
            },
            isError = { },

        )
    }

    /**
     * API 호출 중 Exception이 발생하면 error를 호출한다.
     * Exception을 핸들링 하기 용이하다.
     */
    private fun test4() {
        testItem.value = CoroutineTest("시작", LocalDateTime.now().format(formatter))
        getCoroutineTest4ExceptionUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = {
                it.forEach { i -> testItem.value = i }
                testItem.value = CoroutineTest("끝", LocalDateTime.now().format(formatter))
            },
            isError = {
                Log.d("qweqwe", "it : " + it)
            },

        )
    }

    private fun getUser() {
        getCoroutineTest2GetUserUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = { testItem.value = it },
            isError = { },

        )
    }

    private fun getNews() {
        getCoroutineTest2GetNewsUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = { testItem.value = it },
            isError = { },
        )
    }
}
