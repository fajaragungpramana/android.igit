package com.github.fajaragungpramana.igit.module.setting

import com.github.fajaragungpramana.igit.core.domain.local.model.Setting

sealed class SettingState {

    data class SettingData(val listSetting: List<Setting>) : SettingState()

    data class MessageData(val message: String) : SettingState()

}