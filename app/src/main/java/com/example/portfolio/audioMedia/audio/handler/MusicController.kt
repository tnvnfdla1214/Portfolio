package com.example.portfolio.audioMedia.audio.handler

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ConcatenatingMediaSource2
import androidx.media3.session.MediaSession
import com.example.portfolio.audioMedia.audio.model.PlayTrack
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@UnstableApi
class MusicController @Inject constructor(
    @ApplicationContext private val context: Context,
    val player: ExoPlayer,
    val mediaSession: MediaSession,
) : MusicControl {

    private val currentTrackIndex = MutableLiveData(-1)

    init {
        setupPlayerListener()
    }

    private fun setupPlayerListener() {
        player.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                currentTrackIndex.value = player.currentMediaItemIndex
            }
        })
    }

    override fun selectTrack(track: PlayTrack, tracks: List<PlayTrack>) {
        val playingIndex = tracks.getCurrentlyPlayingIndex(track.mid)
        player.seekTo(playingIndex, 0)
    }

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun stop() {
        player.stop()
    }

    override fun release() {
        player.release()
    }

    override fun prepare() {
        player.prepare()
    }

    override fun isPlaying(): Boolean = player.isPlaying

    override fun seekToProgress(progress: Int) {
        TODO("Not yet implemented")
    }

    override fun nextTrack() {
        TODO("Not yet implemented")
    }

    override fun previousTrack() {
        TODO("Not yet implemented")
    }

    override fun setShuffleMode(shuffleEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setRepeatMode(repeatMode: Int) {
        TODO("Not yet implemented")
    }

    override fun setPlaybackSpeed(speed: Float) {
        TODO("Not yet implemented")
    }

    override fun getCurrentlyPlayingTrackIndex(): LiveData<Int> = currentTrackIndex
    override fun isPlayingTrack(tracks: List<PlayTrack>, track: PlayTrack): Boolean {
        currentTrackIndex.value?.run { return tracks[this].mid == track.mid } ?: return false
    }

    override fun setTracks(tracks: List<PlayTrack>) {
        val concatenatingMediaSource = ConcatenatingMediaSource2.Builder()
        concatenatingMediaSource.useDefaultMediaSourceFactory(context)

        tracks.forEach {
            val mediaItem = it.toBaseMediaSource().build()
            concatenatingMediaSource.add(mediaItem, it.duration)
        }

        player.setMediaSource(concatenatingMediaSource.build())
    }

    private fun List<PlayTrack>.getCurrentlyPlayingIndex(mid: String): Int =
        indexOfFirst { it.mid == mid }
}