package com.github.fajaragungpramana.igit.core.data.local.sql

import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.UserDao
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import com.github.fajaragungpramana.igit.core.extension.connection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SqlRepository @Inject constructor(private val userDao: UserDao) : ISqlRepository {

    override suspend fun getListUser(): Flow<AppResult<List<UserEntity>>> = connection {
        AppResult.Success(userDao.getAll())
    }

}