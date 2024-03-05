package com.github.fajaragungpramana.igit.module.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.core.domain.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val localUseCase: LocalUseCase) : ViewModel() {

    private val _state by lazy { Channel<SettingState>() }
    val state: Flow<SettingState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.SaveSetting -> saveSetting(event.settingEntity)
            is SettingEvent.Setting -> getListSetting()
        }
    }

    private fun saveSetting(settingEntity: SettingEntity): Job = viewModelScope.launch {
        localUseCase.saveSetting(settingEntity).collectLatest { getListSetting() }
    }

    private fun getListSetting(): Job = viewModelScope.launch {
        localUseCase.getListSetting().collectLatest {
            when (it) {
                is AppResult.Success -> _state.send(SettingState.SettingData(it.data.orEmpty()))
                is AppResult.Error -> _state.send(SettingState.MessageData(it.message))
            }
        }
    }

}