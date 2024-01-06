package com.example.portfolio.audioMedia.audio.view.control

class DefaultMediaControl : MediaControl {

    private var onPlayStatusToggleListener: (() -> Unit)? = null
    private var onSeekToListener: ((progress: Int) -> Unit)? = null
    private var onNextTrackListener: (() -> Unit)? = null
    private var onPreviousTrackListener: (() -> Unit)? = null
    private var onShuffleModeChangeListener: ((enabled: Boolean) -> Unit)? = null
    private var onRepeatModeChangeListener: ((repeatMode: Int) -> Unit)? = null
    private var onPlaybackSpeedChangeListener: ((playbackSpeed: Float) -> Unit)? = null

    override fun onPlayStatusChanged() {
        onPlayStatusToggleListener?.invoke()
    }

    override fun setOnPlayStatusToggleListener(listener: () -> Unit) {
        onPlayStatusToggleListener = listener
    }

    override fun seekTo(progress: Int) {
        onSeekToListener?.invoke(progress)
    }

    override fun setOnSeekToListener(listener: (progress: Int) -> Unit) {
        onSeekToListener = listener
    }

    override fun seekToNextTrack() {
        onNextTrackListener?.invoke()
    }

    override fun setOnNextTrackListener(listener: () -> Unit) {
        onNextTrackListener = listener
    }

    override fun seekToPreviousTrack() {
        onPreviousTrackListener?.invoke()
    }

    override fun setOnPreviousTrackListener(listener: () -> Unit) {
        onPreviousTrackListener = listener
    }

    override fun onShuffleModeChanged(shuffleEnabled: Boolean) {
        onShuffleModeChangeListener?.invoke(shuffleEnabled)
    }

    override fun setOnShuffleModeChangeListener(listener: (shuffle: Boolean) -> Unit) {
        onShuffleModeChangeListener = listener
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        onRepeatModeChangeListener?.invoke(repeatMode)
    }

    override fun setOnRepeatModeChangeListener(listener: (repeatMode: Int) -> Unit) {
        onRepeatModeChangeListener = listener
    }

    override fun onPlaybackSpeedChanged(playbackSpeed: Float) {
        onPlaybackSpeedChangeListener?.invoke(playbackSpeed)
    }

    override fun setOnPlaybackSpeedChangeListener(listener: (playbackSpeed: Float) -> Unit) {
        onPlaybackSpeedChangeListener = listener
    }
}