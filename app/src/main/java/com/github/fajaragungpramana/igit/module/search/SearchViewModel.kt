package com.github.fajaragungpramana.igit.module.search

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
class SearchViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<SearchState>() }
    val state: Flow<SearchState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchUser -> getListUser(event.username)
        }
    }

    private fun getListUser(username: String?): Job = viewModelScope.launch {
        userUseCase.getUser(username.orEmpty())

        userUseCase.getListUser(UserRequest(username)).collectLatest {
            _state.send(SearchState.UserData(it))
        }
    }

}