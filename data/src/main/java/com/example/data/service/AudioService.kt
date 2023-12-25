package com.example.data.service

import com.example.data.dataSource.response.MusicsResponse
import retrofit2.http.GET

interface AudioService {

    @GET("mingyu/musics")
    suspend fun getMusics(): MusicsResponse
}