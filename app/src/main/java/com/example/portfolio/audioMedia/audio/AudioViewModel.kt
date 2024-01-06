package com.example.portfolio.audioMedia.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.model.Music
import com.example.domain.usecase.GetMusicListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audioUseCase: GetMusicListUseCase,
) : ViewModel() {

    private val _musics: MutableStateFlow<List<Music>> = MutableStateFlow(listOf())
    val musics: StateFlow<List<Music>> = _musics.asStateFlow()

    init {
        getMusicList()
    }

    private fun getMusicList() {
        audioUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = {
                Log.d("qweqwe", "isLoading")
            },
            isSuccess = {
                _musics.value = it
            },
            isError = {
                Log.d("qweqwe", "isError : " + it.message)
            },
        )
    }
}