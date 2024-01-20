package com.example.portfolio.coroutine

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.model.CoroutineTest
import com.example.domain.usecase.GetCoroutineTest1UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTest1UseCase: GetCoroutineTest1UseCase,
) : ViewModel() {

    private val _itmes: MutableStateFlow<MutableList<CoroutineTest>> =
        MutableStateFlow(mutableListOf())
    val items: StateFlow<MutableList<CoroutineTest>> = _itmes.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    init {
        test1()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun test1() {
        val items: MutableList<CoroutineTest> = mutableListOf()
        items.add(CoroutineTest("시작", LocalDateTime.now().format(formatter)))
        getCoroutineTest1UseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = {
                items.addAll(it)
                items.add(CoroutineTest("끝", LocalDateTime.now().format(formatter)))
                _itmes.value = items
            },
            isError = {
                Log.d("qweqwe", "onError")
            },

        )
    }
}
