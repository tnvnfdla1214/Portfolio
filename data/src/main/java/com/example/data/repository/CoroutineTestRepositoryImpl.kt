package com.example.data.repository

import android.util.Log
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoroutineTestRepositoryImpl @Inject constructor() : CoroutineTestRepository {
    override suspend fun getUser(): String {
        delay(2000)
        Log.d("qweqwe", "getUser")
        return "user"
    }

    override suspend fun getUserPeed(name: String): CoroutineTest {
        delay(2000)
        Log.d("qweqwe", "getUserPeed")
        return CoroutineTest(name, "userPeed")
    }

    override suspend fun getNews(): CoroutineTest {
        delay(2000)
        Log.d("qweqwe", "getNews")
        return CoroutineTest("News", "News222")
    }
}