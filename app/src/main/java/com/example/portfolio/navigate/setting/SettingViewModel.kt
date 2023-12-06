package com.example.portfolio.navigate.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.domain.usecase.GetDeleteModeUseCase
import com.example.domain.usecase.GetSortModeUseCase
import com.example.domain.usecase.SaveDeleteModeUseCase
import com.example.domain.usecase.SaveSortModeUseCase
import com.example.portfolio.worker.CacheDeleteWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val saveSortModeUseCase: SaveSortModeUseCase,
    private val getSortModeUseCase: GetSortModeUseCase,
    private val saveDeleteModeUseCase: SaveDeleteModeUseCase,
    private val getDeleteModeUseCase: GetDeleteModeUseCase,
    private val workManager: WorkManager,
) : ViewModel() {
    // DataStore
    fun saveSortMode(value: String) = viewModelScope.launch {
        saveSortModeUseCase.invoke(value)
    }

    suspend fun getSortMode() = withContext(Dispatchers.IO) {
        getSortModeUseCase.invoke()
    }

    fun saveCacheDeleteMode(value: Boolean) = viewModelScope.launch {
        saveDeleteModeUseCase.invoke(value)
    }

    suspend fun getCacheDeleteMode() = withContext(Dispatchers.IO) {
        getDeleteModeUseCase.invoke()
    }

    // WorkManager
    fun setWork() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<CacheDeleteWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORKER_KEY,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest,
        )
    }

    fun deleteWork() = workManager.cancelUniqueWork(WORKER_KEY)

    fun getWorkStatus(): LiveData<MutableList<WorkInfo>> =
        workManager.getWorkInfosForUniqueWorkLiveData(WORKER_KEY)

    companion object {
        private const val WORKER_KEY = "cache_worker"
    }
}