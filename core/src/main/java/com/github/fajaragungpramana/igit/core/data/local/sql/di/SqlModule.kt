package com.github.fajaragungpramana.igit.core.data.local.sql.di

import com.github.fajaragungpramana.igit.core.data.local.sql.ISqlRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.SqlDatabase
import com.github.fajaragungpramana.igit.core.data.local.sql.SqlRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.SettingDao
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
    fun provideSettingDao(sqlDatabase: SqlDatabase): SettingDao = sqlDatabase.settingDao()

    @Provides
    fun provideRepository(userDao: UserDao, settingDao: SettingDao): ISqlRepository =
        SqlRepository(userDao, settingDao)

}