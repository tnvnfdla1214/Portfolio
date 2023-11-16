package com.example.portfolio.audio.player

import android.content.Intent
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.portfolio.audio.player.notification.AudioNotificationManager
import com.example.portfolio.event.Playlist
import com.example.portfolio.wrapper.EventBus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerSessionService : MediaSessionService() {

    @Inject
    lateinit var player: ExoPlayer

    @Inject
    lateinit var mediaSession: MediaSession

    @Inject
    lateinit var notificationManager: AudioNotificationManager

    override fun onCreate() {
        super.onCreate()

        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_IDLE) {
                    stopSelf()
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                EventBus.post(Playlist.Player.StatusChanged(isPlaying))
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager.startNotificationService(
            mediaSessionService = this,
            mediaSession = mediaSession,
        )

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaSession.release()
        player.takeIf {
            it.playbackState != Player.STATE_IDLE
        }?.stop() // If we release the player, it can't be reused.
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession = mediaSession
}
