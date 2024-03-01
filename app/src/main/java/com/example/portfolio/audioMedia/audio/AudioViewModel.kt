package com.example.portfolio.audioMedia.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
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

@UnstableApi
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
        when {
            !isPlaying() -> {
                Log.d("qweqwe", "1111111")
                setTracks()
                selectTrack(track, _tracks.value)
                play()
            }

            isPlayingTrack(tracks.value, track) -> {
                Log.d("qweqwe", "222222")
                pause()
            }

            else -> {
                Log.d("qweqwe", "3333333")
                selectTrack(track, _tracks.value)
                play()
            }
        }
    }

    private fun setTracks() {
        controller.setTracks(_tracks.value)
    }

    private fun selectTrack(track: PlayTrack, tracks: List<PlayTrack>) {
        controller.selectTrack(track, tracks)
    }

    private fun play() {
        controller.prepare()
        controller.play()
    }

    private fun pause() {
        controller.pause()
    }

    private fun isPlaying(): Boolean = controller.isPlaying()

    private fun isPlayingTrack(tracks: List<PlayTrack>, track: PlayTrack): Boolean =
        controller.isPlayingTrack(tracks, track)

    private fun getMusicList() {
        audioUseCase.invoke(Unit).doWork(
            viewModelScope,
            isLoading = { },
            isSuccess = { _tracks.value = fromMusics(it) },
            isError = { },
        )
    }
}