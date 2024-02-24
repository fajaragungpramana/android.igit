package com.github.fajaragungpramana.igit.module.popularity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularityViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<PopularityState>() }
    val state: Flow<PopularityState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: PopularityEvent) {
        when (event) {
            is PopularityEvent.ListUser -> getListUser(event.userRequest)
        }
    }

    private fun getListUser(userRequest: UserRequest): Job = viewModelScope.launch {
        userUseCase.getListUser(userRequest).collectLatest {
            _state.send(PopularityState.UserData(it))
        }
    }

}