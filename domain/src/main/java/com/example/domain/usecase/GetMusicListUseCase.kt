package com.example.domain.usecase

import com.example.domain.base.ResultStatus
import com.example.domain.base.ResultUseCase
import com.example.domain.model.Music
import com.example.domain.repository.MusicRepository

class GetMusicListUseCase(
    private val musicRepository: MusicRepository,
) : ResultUseCase<Unit, List<Music>>() {
    override suspend fun doWork(params: Unit?): ResultStatus<List<Music>> {
        return try {
            val musics = musicRepository.getMusics()
            ResultStatus.Success(musics)
        } catch (throwable: Throwable) {
            ResultStatus.Error(throwable)
        }
    }
}