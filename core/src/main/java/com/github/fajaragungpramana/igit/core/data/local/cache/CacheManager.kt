package com.github.fajaragungpramana.igit.core.data.local.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        dataStore.edit {
            it[key] = value
        }
    }

    fun <T> get(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.map { it[key] }
    }

    companion object {
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
    }

}