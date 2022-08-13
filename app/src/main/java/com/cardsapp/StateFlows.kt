package com.cardsapp

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycleCoroutineScope: LifecycleCoroutineScope) {
    lifecycleCoroutineScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}