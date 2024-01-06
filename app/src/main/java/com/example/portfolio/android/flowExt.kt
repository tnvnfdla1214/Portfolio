package com.example.portfolio.android

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    action: suspend CoroutineScope.(T) -> Unit,
) {
    lifecycleOwner.lifecycleScope.launchWhenStarted {
        flowWithLifecycle(
            lifecycle = lifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED,
        ).collect { value ->
            action(value)
        }
    }
}

fun <T> Flow<T>.collectDistinctWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    action: suspend CoroutineScope.(T) -> Unit,
) {
    var previousValue: T? = null

    collectWithLifecycle(lifecycleOwner) { value ->
        if (previousValue != value) {
            value?.let { action(it) }
            previousValue = value
        }
    }
}