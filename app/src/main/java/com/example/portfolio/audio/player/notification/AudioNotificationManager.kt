package com.example.portfolio.audio.player.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.ui.PlayerNotificationManager
import com.example.portfolio.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val player: ExoPlayer,
) {
    private var notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun startNotificationService(
        mediaSessionService: MediaSessionService,
        mediaSession: MediaSession,
    ) {
        buildNotification(mediaSession)
        startForegroundNotification(mediaSessionService)
    }

    private fun buildNotification(mediaSession: MediaSession) {
        val notification = PlayerNotificationManager.Builder(context, NOTIFICATION_ID, NOTIFICATION_CHANNEL_ID)
            .setMediaDescriptionAdapter(
                AudioNotificationAdapter(
                    pendingIntent = mediaSession.sessionActivity,
                ),
            )
            .setSmallIconResourceId(R.drawable.ic_launcher_foreground)
            .build()

        notification.setUseFastForwardAction(false)
        notification.setUseFastForwardActionInCompactView(false)
        notification.setUseRewindAction(false)
        notification.setUseRewindActionInCompactView(false)
        notification.setUseNextAction(true)
        notification.setUseNextActionInCompactView(true)
        notification.setUsePreviousAction(true)
        notification.setUsePreviousActionInCompactView(true)
        notification.setPriority(NotificationCompat.PRIORITY_LOW)
        notification.setMediaSessionToken(mediaSession.sessionCompatToken)
        notification.setPlayer(player)
    }

    private fun startForegroundNotification(
        mediaSessionService: MediaSessionService,
    ) {
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setUsesChronometer(false)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        mediaSessionService.startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_LOW,
                ),
            )
        }
    }
}

private const val NOTIFICATION_ID = 408048
private const val NOTIFICATION_CHANNEL_NAME = "audio-book-notification"
private const val NOTIFICATION_CHANNEL_ID = "audio-book-notification-id"
