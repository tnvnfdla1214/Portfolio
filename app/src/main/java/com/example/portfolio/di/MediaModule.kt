package com.example.portfolio.di

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.LoadControl
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.example.portfolio.audioMedia.audio.handler.MusicController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MediaModule {

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes =
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

    @UnstableApi
    @Provides
    @Singleton
    fun provideLoadControl(): LoadControl =
        DefaultLoadControl.Builder()
            .setPrioritizeTimeOverSizeThresholds(true)
            .setBufferDurationsMs(
                360 * MINUTE,
                360 * MINUTE,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS,
            )
            .setBackBuffer(
                360 * MINUTE,
                false,
            )
            .build()

    @Provides
    @Singleton
    @UnstableApi
    fun providePlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes,
        loadControl: LoadControl,
    ): ExoPlayer {
//        val shuffleStatus: Boolean by BooleanPreference(context, "default", Pref.PLAY_SHUFFLE, false)
//        val repeatMode: Int by IntegerPreference(context, "default", Pref.PLAY_REPEAT, ExoPlayer.REPEAT_MODE_OFF)
//        val playbackSpeed: Float by FloatPreference(context, "default", Pref.PLAYBACK_SPEED, 1f)

        return ExoPlayer.Builder(context)
            .setAudioAttributes(audioAttributes, true)
            .setLoadControl(loadControl)
            .setUseLazyPreparation(false)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceMusicControllerHandler(
        @ApplicationContext context: Context,
        player: ExoPlayer,
    ): MusicController = MusicController(
        context = context,
        player = player,
    )
}

private const val MINUTE = 60 * 1000