package com.example.portfolio.audioMedia.audio.model

import android.net.Uri
import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import com.example.domain.model.Music

data class PlayTrack(
    val mid: String,
    val title: String,
    val artist: String,
    val albumArt: String,
    val musicUrl: String,
    val duration: Long,
    var playState: Boolean = false,
) {
    @UnstableApi
    fun toBaseMediaSource(): MediaItem.Builder {
        return MediaItem.Builder()
            .setUri(musicUrl)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(title)
                    .setArtist(artist)
                    .setArtworkUri(Uri.parse(this.albumArt))
                    .setExtras(
                        Bundle().apply {
                            putString("mid", mid)
                            putLong("duration", duration)
                        },
                    )
                    .build(),
            )
    }

    companion object {
        fun fromMusics(musics: List<Music>): List<PlayTrack> {
            return musics.map {
                PlayTrack(
                    mid = it.mid,
                    title = it.title,
                    artist = it.artist,
                    albumArt = it.albumArt,
                    musicUrl = it.url,
                    duration = it.duration,
                )
            }
        }
    }
}