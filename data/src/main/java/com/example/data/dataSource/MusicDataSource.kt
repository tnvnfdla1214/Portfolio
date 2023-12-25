package com.example.data.dataSource

import com.example.data.service.AudioService
import javax.inject.Inject

class MusicDataSource @Inject constructor(
    private val audioService: AudioService
) {
    suspend fun getMusics() = audioService.getMusics()
}