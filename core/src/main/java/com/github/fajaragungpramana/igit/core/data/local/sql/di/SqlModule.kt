package com.github.fajaragungpramana.igit.core.data.local.sql.di

import com.github.fajaragungpramana.igit.core.data.local.sql.ISqlRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.SqlDatabase
import com.github.fajaragungpramana.igit.core.data.local.sql.SqlRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SqlModule {

    @Provides
    fun provideUserDao(sqlDatabase: SqlDatabase): UserDao = sqlDatabase.userDao()

    @Provides
    fun provideRepository(userDao: UserDao): ISqlRepository = SqlRepository(userDao)

}