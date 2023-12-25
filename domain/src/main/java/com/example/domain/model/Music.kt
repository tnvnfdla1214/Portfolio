package com.example.domain.model

data class Music(
    val id: Long,
    val title: String,
    val artist: String,
    val albumArt: String,
    val url: String,
    val duration: Long,
)