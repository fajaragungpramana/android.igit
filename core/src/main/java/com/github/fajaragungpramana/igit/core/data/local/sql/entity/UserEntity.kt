package com.github.fajaragungpramana.igit.core.data.local.sql.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "avatar")
    val avatar: String?,

    @ColumnInfo(name = "full_name")
    val fullName: String?,

    @ColumnInfo(name = "username")
    val username: String?

)