package com.example.portfolio.audioMedia.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.usecase.GetMusicListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audioUseCase: GetMusicListUseCase,
) : ViewModel() {

    fun getMusicList() {
        audioUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = {
                Log.d("qweqwe","1111")
            },
            isSuccess = {
                Log.d("qweqwe","2222")
                Log.d("qweqwe","success : " + it.toString())
            },
            isError = {
                Log.d("qweqwe","error : " + it.message)
            }
        )
    }
}