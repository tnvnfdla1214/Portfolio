package com.example.portfolio.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.model.CoroutineTest
import com.example.domain.usecase.GetCoroutineTest1UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTest1UseCase: GetCoroutineTest1UseCase,
) : ViewModel() {

    private val _itmes: MutableStateFlow<List<CoroutineTest>> = MutableStateFlow(listOf())
    val items: StateFlow<List<CoroutineTest>> = _itmes.asStateFlow()

    init {
        test1()
    }

    private fun test1() {
        Log.d("qweqwe", "test1")
        getCoroutineTest1UseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = { _itmes.value = it },
            isError = {
                Log.d("qweqwe", "onError")
            },

        )
    }
}
