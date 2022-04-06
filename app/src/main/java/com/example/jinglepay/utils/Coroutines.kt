package com.example.jinglepay.utils

import kotlinx.coroutines.*

object Coroutines {

    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }

    fun io(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }

    fun launchPeriodicAsync(repeatMillis: Long, work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).async {
            while (isActive) {
                work()
                delay(repeatMillis)
            }
        }
}