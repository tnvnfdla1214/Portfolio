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
@RequiresApi(Build.VERSION_CODES.O)
class CoroutineTestRepositoryImpl @Inject constructor() : CoroutineTestRepository {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    override suspend fun getUser(): CoroutineTest {
        Log.d("qweqwe", "getUser")
        delay(2000)
        return CoroutineTest("User", LocalDateTime.now().format(formatter))
    }

    override suspend fun getNews(): CoroutineTest {
        Log.d("qweqwe", "getNews")
        delay(1000)
        return CoroutineTest("News", LocalDateTime.now().format(formatter))
    }
}