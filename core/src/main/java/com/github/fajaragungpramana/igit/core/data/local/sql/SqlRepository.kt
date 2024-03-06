package com.github.fajaragungpramana.igit.core.data.local.sql

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.SettingDao
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.UserDao
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import com.github.fajaragungpramana.igit.core.extension.connection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SqlRepository @Inject constructor(
    private val userDao: UserDao,
    private val settingDao: SettingDao
) : ISqlRepository {

    override suspend fun getListUser(): Flow<AppResult<List<UserEntity>>> = connection {
        AppResult.Success(userDao.getAll())
    }

    override suspend fun getUser(username: String): Flow<AppResult<UserEntity>> = connection {
        AppResult.Success(userDao.findByUsername(username))
    }

    override suspend fun saveUser(userEntity: UserEntity): Flow<AppResult<Unit>> = connection {
        AppResult.Success(userDao.save(userEntity))
    }

    override suspend fun deleteUser(username: String): Flow<AppResult<Unit>> = connection {
        AppResult.Success(userDao.delete(username))
    }

    override suspend fun saveSetting(settingEntity: SettingEntity): Flow<AppResult<Unit>> =
        connection { AppResult.Success(settingDao.save(settingEntity)) }

    override suspend fun getListSetting(): Flow<AppResult<List<SettingEntity>>> = connection {
        AppResult.Success(settingDao.getAll())
    }

}