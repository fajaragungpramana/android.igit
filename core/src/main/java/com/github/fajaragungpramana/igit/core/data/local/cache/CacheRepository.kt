package com.github.fajaragungpramana.igit.core.data.local.cache

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheRepository @Inject constructor(private val cacheManager: CacheManager) :
    ICacheRepository {

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        cacheManager.save(key, value)
    }

    override suspend fun <T> get(key: Preferences.Key<T>): Flow<T?> = cacheManager.get(key)

}