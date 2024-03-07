package com.github.fajaragungpramana.igit.core.data.local.sql.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity

@Dao
interface SettingDao {

    @Insert
    fun save(settingEntity: SettingEntity)

    @Query("SELECT * FROM settings")
    fun getAll(): List<SettingEntity>

}