package com.example.domain.model

data class Music(
    val mid: String,
    val title: String,
    val artist: String,
    val albumArt: String,
    val url: String,
    val duration: Long,
)