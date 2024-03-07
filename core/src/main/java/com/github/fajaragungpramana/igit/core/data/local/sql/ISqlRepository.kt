package com.github.fajaragungpramana.igit.core.data.local.sql

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ISqlRepository {

    suspend fun getListUser(): Flow<AppResult<List<UserEntity>>>

    suspend fun getUser(username: String): Flow<AppResult<UserEntity>>

    suspend fun saveUser(userEntity: UserEntity): Flow<AppResult<Unit>>

    suspend fun deleteUser(username: String): Flow<AppResult<Unit>>

    suspend fun saveSetting(settingEntity: SettingEntity): Flow<AppResult<Unit>>

    suspend fun getListSetting(): Flow<AppResult<List<SettingEntity>>>

}