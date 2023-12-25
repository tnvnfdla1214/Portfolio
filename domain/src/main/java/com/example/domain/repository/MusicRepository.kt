package com.example.domain.repository

import com.example.domain.model.Music

interface MusicRepository {
    suspend fun getMusics(): List<Music>
}