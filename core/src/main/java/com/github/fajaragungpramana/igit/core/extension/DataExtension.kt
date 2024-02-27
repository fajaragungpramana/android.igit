package com.github.fajaragungpramana.igit.core.extension

import com.github.fajaragungpramana.igit.core.app.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

suspend inline fun <T : Any> connection(crossinline run: suspend () -> AppResult<T>): Flow<AppResult<T>> = channelFlow {
    try {
        send(run())
    } catch (e: Exception) {
        send(AppResult.Error(e.message.orEmpty()))
    }
}.flowOn(Dispatchers.IO)

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}