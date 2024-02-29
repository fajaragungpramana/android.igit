package com.github.fajaragungpramana.igit.core.data.local.sql.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>

    @Insert
    suspend fun save(users: UserEntity)

}