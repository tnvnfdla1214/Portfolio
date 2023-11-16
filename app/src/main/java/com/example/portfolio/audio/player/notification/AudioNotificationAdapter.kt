package com.example.portfolio.audio.player.notification

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerNotificationManager
import com.example.portfolio.utils.BitmapHelper

@UnstableApi
class AudioNotificationAdapter(
    private val pendingIntent: PendingIntent?,
) : PlayerNotificationManager.MediaDescriptionAdapter {

    override fun createCurrentContentIntent(player: Player): PendingIntent? = pendingIntent

    override fun getCurrentContentTitle(player: Player): CharSequence =
        player.mediaMetadata.title ?: ""

    override fun getCurrentContentText(player: Player): CharSequence =
        player.mediaMetadata.subtitle ?: ""

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback,
    ): Bitmap? {
        return try {
            BitmapHelper.getBitmap(player.mediaMetadata.artworkUri.toString(), true)
        } catch (e: java.lang.NullPointerException) {
            null
        }
    }
}
