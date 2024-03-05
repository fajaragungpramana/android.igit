package com.github.fajaragungpramana.igit.core.domain.local

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.domain.local.model.Setting
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {

    suspend fun saveSetting(settingEntity: SettingEntity): Flow<Boolean>

    suspend fun getListSetting(): Flow<AppResult<List<Setting>>>

}