package com.github.fajaragungpramana.igit.module.main

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.core.data.local.cache.CacheManager
import com.github.fajaragungpramana.igit.core.domain.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localUseCase: LocalUseCase) : ViewModel() {

    private val _state by lazy { Channel<MainState>() }
    val state: Flow<MainState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Theme -> getAppTheme()
        }
    }

    private fun getAppTheme(): Job = viewModelScope.launch {
        val isDark = localUseCase.get(CacheManager.IS_DARK_THEME).first() ?: false
        _state.send(MainState.AppTheme(isDark))
    }

    fun <T> setCache(key: Preferences.Key<T>, value: T): Job = viewModelScope.launch {
        localUseCase.save(key, value)
    }

}