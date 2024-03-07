package com.github.fajaragungpramana.igit.core.data.local.sql

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.SettingDao
import com.github.fajaragungpramana.igit.core.data.local.sql.dao.UserDao
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity

@Database(entities = [UserEntity::class, SettingEntity::class], version = SqlDatabase.VERSION)
abstract class SqlDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun settingDao(): SettingDao

    companion object {
        const val VERSION = 1
    }

}