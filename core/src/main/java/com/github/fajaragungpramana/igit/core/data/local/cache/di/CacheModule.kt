package com.github.fajaragungpramana.igit.core.data.local.cache.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.github.fajaragungpramana.igit.core.constant.CoreConstant
import com.github.fajaragungpramana.igit.core.data.local.cache.CacheManager
import com.github.fajaragungpramana.igit.core.data.local.cache.CacheRepository
import com.github.fajaragungpramana.igit.core.data.local.cache.ICacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    applicationContext,
                    CoreConstant.APP_SETTING
                )
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { applicationContext.preferencesDataStoreFile(CoreConstant.APP_SETTING) }
        )

    @Provides
    fun provideCacheManager(dataStore: DataStore<Preferences>): CacheManager =
        CacheManager(dataStore)

    @Provides
    fun provideCacheRepository(cacheManager: CacheManager): ICacheRepository =
        CacheRepository(cacheManager)

}