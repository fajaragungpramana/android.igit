package com.github.fajaragungpramana.igit.core.data.local.sql

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ISqlRepository {

    suspend fun getListUser(): Flow<AppResult<List<UserEntity>>>

}