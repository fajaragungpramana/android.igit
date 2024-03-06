package com.github.fajaragungpramana.igit.core.data.local.sql.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingEntity(

    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "code")
    val code: String

) {

    enum class Code {
        THEME
    }

}