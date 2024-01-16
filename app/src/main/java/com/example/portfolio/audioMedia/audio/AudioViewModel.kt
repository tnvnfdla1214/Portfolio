package com.example.portfolio.audioMedia.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.doWork
import com.example.domain.usecase.GetMusicListUseCase
import com.example.portfolio.audioMedia.audio.handler.MusicController
import com.example.portfolio.audioMedia.audio.model.PlayTrack
import com.example.portfolio.audioMedia.audio.model.PlayTrack.Companion.fromMusics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audioUseCase: GetMusicListUseCase,
    private val controller: MusicController,
) : ViewModel() {

    private val _tracks: MutableStateFlow<List<PlayTrack>> = MutableStateFlow(listOf())
    val tracks: StateFlow<List<PlayTrack>> = _tracks.asStateFlow()

    init {
        getMusicList()
    }

    fun selectTrackList(track: PlayTrack) {
        Log.d("qweqwe","1111")
        when {
            !isPlaying() -> {
                Log.d("qweqwe","isPlaying")
                setTracks()
                selectTrack(track)
                play()
            }

            isPlayingTrack(track) -> {
                Log.d("qweqwe","isPlayingTrack")
                pause()
            }

            else -> {
                selectTrack(track)
                play()
            }
        }
    }

    private fun setTracks() {
        controller.setTracks(_tracks.value)
    }

    private fun selectTrack(track: PlayTrack) {
        controller.selectTrack(track)
    }

    private fun play() {
        controller.play()
    }

    private fun pause() {
        controller.pause()
    }

    private fun isPlaying(): Boolean = controller.isPlaying()

    private fun isPlayingTrack(track: PlayTrack): Boolean = controller.isPlayingTrack(track)

    private fun getMusicList() {
        audioUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = {
                Log.d("qweqwe", "isLoading")
            },
            isSuccess = {
                _tracks.value = fromMusics(it)
            },
            isError = {
                Log.d("qweqwe", "isError : " + it.message)
            },
        )
    }
}