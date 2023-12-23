package com.example.domain.model

data class Music(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val albumId: Long,
    val albumArt: String,
    val isFavorite: Boolean,
)