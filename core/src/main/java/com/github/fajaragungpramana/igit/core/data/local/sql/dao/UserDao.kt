package com.github.fajaragungpramana.igit.core.data.local.sql.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE username IN (:username) LIMIT 1")
    fun findByUsername(username: String): UserEntity

    @Insert
    fun save(user: UserEntity)

    @Query("DELETE FROM users WHERE username IN (:username)")
    fun delete(username: String)

}