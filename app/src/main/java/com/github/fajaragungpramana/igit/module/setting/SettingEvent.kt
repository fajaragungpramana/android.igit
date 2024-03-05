package com.github.fajaragungpramana.igit.module.setting

import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity

sealed class SettingEvent {

    data class SaveSetting(val settingEntity: SettingEntity) : SettingEvent()

    data object Setting : SettingEvent()

}