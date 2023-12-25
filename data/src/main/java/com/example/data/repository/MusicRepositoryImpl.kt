package com.example.data.repository

import com.example.data.dataSource.MusicDataSource
import com.example.domain.model.Music
import com.example.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {
    override suspend fun getMusics(): List<Music> = musicDataSource.getMusics().data
}