package com.example.portfolio.audioMedia.audio.view.control

interface MediaControl {

    fun onPlayStatusChanged()

    fun setOnPlayStatusToggleListener(listener: () -> Unit)

    fun seekTo(progress: Int)

    fun setOnSeekToListener(listener: (progress: Int) -> Unit)

    fun seekToNextTrack()

    fun setOnNextTrackListener(listener: () -> Unit)

    fun seekToPreviousTrack()

    fun setOnPreviousTrackListener(listener: () -> Unit)

    fun onShuffleModeChanged(shuffleEnabled: Boolean)

    fun setOnShuffleModeChangeListener(listener: (shuffle: Boolean) -> Unit)

    fun onRepeatModeChanged(repeatMode: Int)

    fun setOnRepeatModeChangeListener(listener: (repeatMode: Int) -> Unit)

    fun onPlaybackSpeedChanged(playbackSpeed: Float)

    fun setOnPlaybackSpeedChangeListener(listener: (playbackSpeed: Float) -> Unit)
}