package com.example.portfolio.audioMedia.audio.handler

import androidx.lifecycle.LiveData
import com.example.domain.model.Music
import com.example.portfolio.audioMedia.audio.model.PlayTrack

interface MusicControl {

    fun selectTrack(track: PlayTrack, tracks: List<PlayTrack>)

    fun play()

    fun pause()

    fun stop()

    fun release()

    fun isPlaying(): Boolean

    fun seekToProgress(progress: Int)

    fun nextTrack()

    fun previousTrack()

    fun setShuffleMode(shuffleEnabled: Boolean)

    fun setRepeatMode(repeatMode: Int)

    fun setPlaybackSpeed(speed: Float)

    fun getCurrentlyPlayingTrack(): LiveData<Music>

    fun isPlayingTrack(track: PlayTrack): Boolean

    fun setTracks(tracks: List<PlayTrack>)
}