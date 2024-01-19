package com.example.domain.repository

import com.example.domain.model.CoroutineTest

interface CoroutineTestRepository {
    suspend fun getUser(): String

    suspend fun getUserPeed(name: String): CoroutineTest

    suspend fun getNews(): CoroutineTest
}