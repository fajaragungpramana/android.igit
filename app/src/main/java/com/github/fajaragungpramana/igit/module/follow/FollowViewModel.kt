package com.github.fajaragungpramana.igit.module.follow

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
class FollowViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<FollowState>() }
    val state: Flow<FollowState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: FollowEvent) {
        when (event) {
            is FollowEvent.ListFollow -> getListFollow(event.userRequest)
        }
    }

    private fun getListFollow(userRequest: UserRequest): Job = viewModelScope.launch {
        userUseCase.getListUser(userRequest).collectLatest {
            _state.send(FollowState.UserData(it))
        }
    }

}