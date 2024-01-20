package com.example.domain.repository

import com.example.domain.model.CoroutineTest

interface CoroutineTestRepository {
    suspend fun getUser(): CoroutineTest

    suspend fun getNews(): CoroutineTest

    suspend fun getUserName(): String
    suspend fun getUserNews(name: String): CoroutineTest
}