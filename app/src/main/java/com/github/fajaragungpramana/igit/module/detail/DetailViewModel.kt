package com.github.fajaragungpramana.igit.module.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.constant.EspressoIdlingResource
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<DetailState>() }
    val state: Flow<DetailState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.User -> getUser(event.username)
        }
    }

    private fun getUser(username: String): Job = viewModelScope.launch {
        EspressoIdlingResource.increment()

        _state.send(DetailState.UserLoading(true))
        userUseCase.getUser(username).collectLatest {
            if (!EspressoIdlingResource.idlingResource.isIdleNow)
                EspressoIdlingResource.decrement()

            when (it) {
                is AppResult.Success -> {
                    _state.send(DetailState.UserData(it.data ?: User()))
                    _state.send(DetailState.UserLoading(false))
                }
                is AppResult.Error -> _state.send(DetailState.MessageData(it.message))
            }
        }
    }

}