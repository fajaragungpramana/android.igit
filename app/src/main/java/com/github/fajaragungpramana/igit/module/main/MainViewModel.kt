package com.github.fajaragungpramana.igit.module.main

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.core.domain.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localUseCase: LocalUseCase) : ViewModel() {

    fun <T> setCache(key: Preferences.Key<T>, value: T): Job = viewModelScope.launch {
        localUseCase.save(key, value)
    }

}