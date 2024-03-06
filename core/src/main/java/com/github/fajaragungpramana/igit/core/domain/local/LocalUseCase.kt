package com.github.fajaragungpramana.igit.core.domain.local

import androidx.datastore.preferences.core.Preferences
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.domain.local.model.Setting
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {

    suspend fun saveSetting(settingEntity: SettingEntity): Flow<Boolean>

    suspend fun getListSetting(): Flow<AppResult<List<Setting>>>

    suspend fun <T> save(key: Preferences.Key<T>, value: T)

    suspend fun <T> get(key: Preferences.Key<T>): Flow<T?>

}