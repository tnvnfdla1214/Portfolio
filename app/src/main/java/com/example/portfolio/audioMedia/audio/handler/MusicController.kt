package com.example.portfolio.audioMedia.audio.handler

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ConcatenatingMediaSource2
import androidx.media3.exoplayer.source.MediaSource
import com.example.domain.model.Music
import com.example.portfolio.audioMedia.audio.model.PlayTrack
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MusicController @Inject constructor(
    @ApplicationContext private val context: Context,
    val player: ExoPlayer,
) : MusicControl {

    private val currentTrack: MutableLiveData<Music> = MutableLiveData()
    override fun selectTrack(track: PlayTrack, tracks: List<PlayTrack>) {
        //player.seekTo()
    }

    override fun play() { player.play() }

    override fun pause() { player.pause() }

    override fun stop() { player.stop() }

    override fun release() { player.release() }

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

    override fun getCurrentlyPlayingTrack(): LiveData<Music> = currentTrack
    override fun isPlayingTrack(track: PlayTrack): Boolean {
        TODO("Not yet implemented")
    }

    @UnstableApi
    override fun setTracks(tracks: List<PlayTrack>) {
        val mediaSourceBuilder = ConcatenatingMediaSource2.Builder()
        mediaSourceBuilder.useDefaultMediaSourceFactory(context)

        tracks.forEach {
            val mediaItem = it.toBaseMediaSource().build()
            mediaSourceBuilder.add(mediaItem, it.duration)
        }

        player.setMediaSource(mediaSourceBuilder.build())
    }

//    @UnstableApi
//    private fun List<PlayTrack>.getCurrentlyPlayingIndex(track: PlayTrack): Int =
//        indexOfFirst { it.mediaItem.mediaMetadata.extras?.getLong("id") == track.id }
}