package com.github.fajaragungpramana.igit.core.data.local.cache

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface ICacheRepository {

    suspend fun <T> save(key: Preferences.Key<T>, value: T)

    suspend fun <T> get(key: Preferences.Key<T>): Flow<T?>

}