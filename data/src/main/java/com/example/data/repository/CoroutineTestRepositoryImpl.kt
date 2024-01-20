package com.example.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.domain.model.CoroutineTest
import com.example.domain.repository.CoroutineTestRepository
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoroutineTestRepositoryImpl @Inject constructor() : CoroutineTestRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    override suspend fun getUser(): String {
        Log.d("qweqwe", "getUser")
        delay(2000)
        return "UserPeed"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getUserPeed(name: String): CoroutineTest {
        delay(2000)
        Log.d("qweqwe", "getUserPeed")
        return CoroutineTest(name, LocalDateTime.now().format(formatter))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getNews(): CoroutineTest {
        Log.d("qweqwe", "getNews")
        delay(2000)
        return CoroutineTest("News", LocalDateTime.now().format(formatter))
    }
}