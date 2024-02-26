package com.github.fajaragungpramana.igit.module.repo

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
class RepoViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<RepoState>() }
    val state: Flow<RepoState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: RepoEvent) {
        when (event) {
            is RepoEvent.ListRepo -> getListRepo(event.userRequest)
        }
    }

    private fun getListRepo(userRequest: UserRequest): Job = viewModelScope.launch {
        userUseCase.getListRepo(userRequest).collectLatest {
            _state.send(RepoState.RepoData(it))
        }
    }

}